package com.krilicar.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelDTO {

    private Long id;

    // Dans BrandDTO.java et ModelDTO.java
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String name;

    /**
     * On utilise uniquement l'ID de la marque pour simplifier les requêtes.
     * Le Mapper se chargera de faire le lien avec l'entité Brand.
     */
    private Long brandId;
}