package com.krilicar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@SuperBuilder // Changé pour SuperBuilder
@EqualsAndHashCode(callSuper = true) // Changé pour l'héritage
public class Company extends BaseEntity {
    private String name;
    // Ajoute ici d'autres champs plus tard (adresse, logo, etc.)
}