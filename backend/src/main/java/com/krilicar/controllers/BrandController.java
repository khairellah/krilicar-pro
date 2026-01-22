package com.krilicar.controllers;

import com.krilicar.dtos.BrandDTO;
import com.krilicar.services.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody  BrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.saveBrand(brandDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody @Valid BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.updateBrand(id, brandDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}