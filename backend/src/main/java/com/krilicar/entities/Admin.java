package com.krilicar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "admin_id")
@Getter @Setter
@NoArgsConstructor
@SuperBuilder // Important pour l'héritage avec AppUser
public class Admin extends AppUser {
    // Tu pourras ajouter des champs spécifiques aux admins ici plus tard
}