package com.krilicar.entities;

import com.krilicar.enums.*;
import jakarta.persistence.*; // Obligatoire pour Spring Boot 3
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Pour utiliser le pattern builder avec l'héritage de BaseEntity
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseEntity {

    @Column(unique = true, nullable = false, length = 17)
    private String vin;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gearbox gearbox;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private CarColor color;

    @Lob
    @Column(columnDefinition = "TEXT") // Meilleure compatibilité avec MySQL 8
    private String description;

    @Column(nullable = false)
    private Integer nbrSeats;

    @Column(nullable = false)
    private Double price; // Prix par jour

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default // AJOUTER CECI pour fixer le warning
    private CarAvailability availability = CarAvailability.AVAILABLE;

    // --- RELATIONS ---
    // Note : On les laisse actives si tu as créé les classes Brand, Model et Company (même vides)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // Pour l'instant, on peut laisser ces listes commentées si tu n'as pas encore créé CarImage et WishList
    /*
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> images;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<WishList> wishLists;
    */
}