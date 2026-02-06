package com.krilicar.services.impl;

import com.krilicar.dtos.CarDTO;
import com.krilicar.entities.Car;
import com.krilicar.entities.Company;
import com.krilicar.entities.Model;
import com.krilicar.exceptions.DuplicateResourceException;
import com.krilicar.exceptions.ResourceNotFoundException;
import com.krilicar.mappers.CarMapper;
import com.krilicar.repositories.CarRepository;
import com.krilicar.repositories.CompanyRepository;
import com.krilicar.repositories.ModelRepository;
import com.krilicar.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final CompanyRepository companyRepository;
    private final CarMapper carMapper;

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        // 1. Vérifier si le VIN existe déjà (Règle métier stricte)
        if (carRepository.findByVin(carDTO.getVin()).isPresent()) {
            throw new DuplicateResourceException("Voiture", "VIN", carDTO.getVin());
        }

        // 2. Vérifier l'existence du modèle et de l'agence
        Model model = modelRepository.findById(carDTO.getModelId())
                .orElseThrow(() -> new ResourceNotFoundException("Modèle", "id", carDTO.getModelId()));

        Company company = companyRepository.findById(carDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Agence", "id", carDTO.getCompanyId()));

        // 3. Mapper le DTO en entité et injecter les relations
        Car car = carMapper.toEntity(carDTO);
        car.setModel(model);
        car.setCompany(company);

        // 4. Sauvegarder
        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CarDTO getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture", "id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDTO> getCarsByBrand(String brandName) {
        return carRepository.findByModelBrandNameIgnoreCase(brandName).stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture", "id", id));

        // Mise à jour des champs simples via les données du DTO
        existingCar.setYear(carDTO.getYear());
        existingCar.setMileage(carDTO.getMileage());
        existingCar.setPrice(carDTO.getPrice());
        existingCar.setColor(carDTO.getColor());
        existingCar.setGearbox(carDTO.getGearbox());
        existingCar.setFuelType(carDTO.getFuelType());
        existingCar.setNbrSeats(carDTO.getNbrSeats());
        existingCar.setAvailability(carDTO.getAvailability());
        existingCar.setDescription(carDTO.getDescription());

        // Si le modèle a changé, on recharge le nouvel objet Model
        if (!existingCar.getModel().getId().equals(carDTO.getModelId())) {
            Model newModel = modelRepository.findById(carDTO.getModelId())
                    .orElseThrow(() -> new ResourceNotFoundException("Modèle", "id", carDTO.getModelId()));
            existingCar.setModel(newModel);
        }

        return carMapper.toDto(carRepository.save(existingCar));
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException("Voiture", "id", id);
        }
        carRepository.deleteById(id);
    }
}