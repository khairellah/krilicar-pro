package com.krilicar.services.impl;

import com.krilicar.dtos.ModelDTO;
import com.krilicar.entities.Model;
import com.krilicar.exceptions.DuplicateResourceException;
import com.krilicar.exceptions.ResourceNotFoundException;
import com.krilicar.mappers.ModelMapper;
import com.krilicar.repositories.BrandRepository;
import com.krilicar.repositories.ModelRepository;
import com.krilicar.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Override
    public ModelDTO saveModel(ModelDTO modelDTO) {
        // 1. Vérifier si la marque existe
        if (!brandRepository.existsById(modelDTO.getBrandId())) {
            throw new ResourceNotFoundException("Marque", "id", modelDTO.getBrandId());
        }
        // 2. Vérifier si le modèle existe déjà pour cette marque
        modelRepository.findByNameIgnoreCaseAndBrandId(modelDTO.getName(), modelDTO.getBrandId())
                .ifPresent(m -> { throw new DuplicateResourceException("Modèle", "nom", modelDTO.getName()); });

        Model model = modelMapper.toEntity(modelDTO);
        return modelMapper.toDto(modelRepository.save(model));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelDTO> getAllModels() {
        return modelMapper.toDtoList(modelRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelDTO> getModelsByBrand(Long brandId) {
        // On vérifie d'abord si la marque existe
        if (!brandRepository.existsById(brandId)) {
            throw new ResourceNotFoundException("Marque", "id", brandId);
        }
        return modelMapper.toDtoList(modelRepository.findByBrandId(brandId));
    }

    @Override
    @Transactional(readOnly = true)
    public ModelDTO getModelById(Long id) {
        return modelRepository.findById(id)
                .map(modelMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Modèle", "id", id));
    }

    @Override
    public ModelDTO updateModel(Long id, ModelDTO modelDTO) {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modèle", "id", id));

        existingModel.setName(modelDTO.getName());
        return modelMapper.toDto(modelRepository.save(existingModel));
    }

    @Override
    public void deleteModel(Long id) {
        if (!modelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Modèle", "id", id);
        }
        modelRepository.deleteById(id);
    }
}