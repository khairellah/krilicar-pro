package com.krilicar.dtos;

import com.krilicar.enums.City;
import com.krilicar.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompanyDTO {
    private Long id;

    // Champs hérités de AppUser
    @Email(message = "Email invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "Le nom (lastName) est obligatoire")
    private String lastName; // Utilisé comme nom de l'agence

    private String phone;
    private Role role;

    // Champs spécifiques Company
    private String landline;
    private City city;
    private String description;
    private Boolean isBooster;
}