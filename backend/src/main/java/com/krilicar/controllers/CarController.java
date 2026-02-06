package com.krilicar.controllers;

import com.krilicar.dtos.CarDTO;
import com.krilicar.services.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@CrossOrigin("*") // Autorise les requêtes depuis Angular (localhost:4200)
public class CarController {

    private final CarService carService;

    // 1. Ajouter une nouvelle voiture
    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) {
        CarDTO savedCar = carService.saveCar(carDTO);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    // 2. Récupérer toutes les voitures (Catalogue complet)
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    // 3. Récupérer une voiture par son ID
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    // 4. Filtrer les voitures par nom de marque (ex: /api/cars/brand/BMW)
    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@PathVariable String brandName) {
        return ResponseEntity.ok(carService.getCarsByBrand(brandName));
    }

    // 5. Mettre à jour les informations d'une voiture
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id, carDTO));
    }

    // 6. Supprimer une voiture
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}