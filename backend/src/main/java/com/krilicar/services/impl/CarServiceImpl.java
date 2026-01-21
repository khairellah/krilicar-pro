package com.krilicar.services.impl;

import com.krilicar.dtos.CarDTO;
import com.krilicar.entities.Car;
import com.krilicar.repositories.CarRepository;
import com.krilicar.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CarDTO mapToDTO(Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .vin(car.getVin())
                .year(car.getYear())
                .mileage(car.getMileage())
                .brandName(car.getBrand().getName())
                .modelName(car.getModel().getName())
                .price(car.getPrice())
                .fuelType(car.getFuelType().name())
                .build();
    }
}