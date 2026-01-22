package com.krilicar.services;

import com.krilicar.dtos.ModelDTO;
import java.util.List;

public interface ModelService {

    /**
     * Crée un nouveau modèle associé à une marque
     */
    ModelDTO saveModel(ModelDTO modelDTO);

    /**
     * Récupère tous les modèles, toutes marques confondues
     */
    List<ModelDTO> getAllModels();

    /**
     * Récupère tous les modèles d'une marque spécifique (ex: tous les modèles de BMW)
     */
    List<ModelDTO> getModelsByBrand(Long brandId);

    /**
     * Récupère un modèle par son ID
     */
    ModelDTO getModelById(Long id);

    /**
     * Met à jour les informations d'un modèle
     */
    ModelDTO updateModel(Long id, ModelDTO modelDTO);

    /**
     * Supprime un modèle
     */
    void deleteModel(Long id);
}