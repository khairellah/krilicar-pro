package com.krilicar.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private String vin;
    private Integer year;
    private Integer mileage;
    private String brandName; // On envoie juste le nom, c'est plus simple
    private String modelName;
    private Double price;
    private String fuelType;
}