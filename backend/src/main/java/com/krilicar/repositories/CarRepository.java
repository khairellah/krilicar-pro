package com.krilicar.repositories;

import com.krilicar.entities.Car;
import com.krilicar.enums.CarAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Spring Boot va générer la requête SQL tout seul grâce au nom de la méthode !
    List<Car> findByBrandName(String brandName);

    // On cherche par le champ "availability" et on passe le statut souhaité en paramètre
    List<Car> findByAvailability(CarAvailability availability);
}