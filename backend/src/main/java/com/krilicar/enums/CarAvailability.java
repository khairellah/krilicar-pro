package com.krilicar.enums;

public enum CarAvailability {
    AVAILABLE,  // La voiture est prête et peut être louée (état général de base).
    MAINTENANCE,// La voiture est en révision ou réparation, non louable.
    DAMAGED,    // La voiture a subi des dégâts nécessitant une immobilisation longue.
    UNAVAILABLE // Statut général pour les autres raisons (retrait de la flotte, etc.).
}