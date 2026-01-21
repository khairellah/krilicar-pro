package com.krilicar.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id; // Correspondra au id de BaseEntity
    private String name;
}