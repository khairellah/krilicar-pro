package com.krilicar.services.impl;

import com.krilicar.dtos.CarDTO;
import com.krilicar.mappers.CarMapper; // Importation du nouveau mapper
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
    private final CarMapper carMapper; // Injection automatique de l'implémentation générée

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDto) // Utilisation du mapper automatique
                .collect(Collectors.toList());
    }

}