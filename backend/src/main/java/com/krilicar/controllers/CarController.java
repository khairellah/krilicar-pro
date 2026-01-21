package com.krilicar.controllers;

import com.krilicar.dtos.CarDTO;
import com.krilicar.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService carService; // On utilise le service, plus le repository !

    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }
}