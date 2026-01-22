package com.krilicar.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id; // Correspondra au id de BaseEntity
    // Dans BrandDTO.java et ModelDTO.java
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String name;
}