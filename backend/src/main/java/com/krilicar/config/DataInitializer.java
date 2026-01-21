package com.krilicar.config;

import com.krilicar.entities.*;
import com.krilicar.enums.*;
import com.krilicar.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository; // Créez cette interface si besoin
    private final ModelRepository modelRepository; // Créez cette interface si besoin

    @Override
    public void run(String... args) {
        if (carRepository.count() == 9000) {
            // 1. Créer une marque
            Brand bmw = new Brand();
            bmw.setName("BMW");
            brandRepository.save(bmw);

            // 2. Créer un modèle
            Model m3 = new Model();
            m3.setName("M3");
            modelRepository.save(m3);

            // 3. Créer la voiture
            Car car = Car.builder()
                    .brand(bmw)
                    .model(m3)
                    .vin("WBS12345678901234")
                    .year(2024)
                    .mileage(100)
                    .gearbox(Gearbox.AUTOMATIC)
                    .fuelType(FuelType.GASOLINE)
                    .nbrSeats(5)
                    .price(150.0)
                    .availability(CarAvailability.AVAILABLE)
                    .build();

            carRepository.save(car);
            System.out.println(">> Une voiture de test a été insérée dans la base Docker !");
        }
    }
}