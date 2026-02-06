package com.krilicar.dtos;

import com.krilicar.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CarDTO {
    private Long id;

    @NotBlank @Size(min = 17, max = 17)
    private String vin;

    @Min(1900) @Max(2026)
    private Integer year;

    @PositiveOrZero
    private Integer mileage;

    @NotNull private Gearbox gearbox;
    @NotNull private FuelType fuelType;
    @NotNull private CarColor color;

    private String description;

    @Min(1) @Max(9)
    private Integer nbrSeats;

    @Positive
    private Double price;

    private CarAvailability availability;

    @NotNull private Long modelId;
    @NotNull private Long companyId;

    // --- Champs rajoutés pour corriger l'erreur MapStruct ---
    private String modelName;  // Sera rempli par model.name
    private String brandName;  // Sera rempli par model.brand.name
}