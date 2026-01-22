package com.krilicar.controllers;

import com.krilicar.dtos.ModelDTO;
import com.krilicar.services.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<ModelDTO> create(@Valid @RequestBody ModelDTO modelDTO) {
        return new ResponseEntity<>(modelService.saveModel(modelDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ModelDTO>> getAll() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModelById(id));
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ModelDTO>> getByBrand(@PathVariable Long brandId) {
        return ResponseEntity.ok(modelService.getModelsByBrand(brandId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelDTO> update(@PathVariable Long id, @RequestBody ModelDTO modelDTO) {
        return ResponseEntity.ok(modelService.updateModel(id, modelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}