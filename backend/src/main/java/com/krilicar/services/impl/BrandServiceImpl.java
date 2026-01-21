package com.krilicar.services.impl;

import com.krilicar.dtos.BrandDTO;
import com.krilicar.entities.Brand;
import com.krilicar.exceptions.DuplicateResourceException;
import com.krilicar.exceptions.ResourceNotFoundException;
import com.krilicar.mappers.BrandMapper;
import com.krilicar.repositories.BrandRepository;
import com.krilicar.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandDTO saveBrand(BrandDTO brandDTO) {
        // Vérification du doublon avant insertion
        brandRepository.findByNameIgnoreCase(brandDTO.getName())
                .ifPresent(b -> {
                    throw new DuplicateResourceException("Marque", "nom", brandDTO.getName());
                });

        Brand brand = brandMapper.toEntity(brandDTO);
        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toDtoList(brands);
    }

    @Override
    @Transactional(readOnly = true)
    public BrandDTO getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Marque", "id", id));
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marque", "id", id));

        // Vérifier si le nouveau nom n'est pas déjà pris par une AUTRE marque
        brandRepository.findByNameIgnoreCase(brandDTO.getName())
                .ifPresent(b -> {
                    if (!b.getId().equals(id)) {
                        throw new DuplicateResourceException("Marque", "nom", brandDTO.getName());
                    }
                });

        existingBrand.setName(brandDTO.getName());
        return brandMapper.toDto(brandRepository.save(existingBrand));
    }

    @Override
    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Marque", "id", id);
        }
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return brandRepository.findByNameIgnoreCase(name).isPresent();
    }
}