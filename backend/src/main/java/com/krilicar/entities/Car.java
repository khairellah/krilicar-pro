package com.krilicar.entities;

import com.krilicar.enums.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Car extends BaseEntity {

    @Column(unique = true, nullable = false, length = 17)
    private String vin; // Identifiant unique mondial

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private CarColor color;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer nbrSeats;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CarAvailability availability = CarAvailability.AVAILABLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}