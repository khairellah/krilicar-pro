package com.krilicar.repositories;

import com.krilicar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Utile pour vérifier si une marque existe déjà avant de la créer
    Optional<Brand> findByNameIgnoreCase(String name);
}