package com.krilicar.repositories;

import com.krilicar.entities.Car;
import com.krilicar.enums.CarAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // 1. Recherche par Marque (Navigation: Car -> Model -> Brand -> Name)
    List<Car> findByModelBrandNameIgnoreCase(String brandName);

    // 2. Recherche par Modèle (Navigation: Car -> Model -> Name)
    List<Car> findByModelNameIgnoreCase(String modelName);

    // 3. Recherche par Disponibilité (Déjà existant)
    List<Car> findByAvailability(CarAvailability availability);

    // 4. Recherche par VIN (Utile pour vérifier l'unicité à la création)
    Optional<Car> findByVin(String vin);

    // 5. Recherche par Agence
    List<Car> findByCompanyId(Long companyId);
}