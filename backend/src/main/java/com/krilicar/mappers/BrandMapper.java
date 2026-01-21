package com.krilicar.mappers;

import com.krilicar.dtos.BrandDTO;
import com.krilicar.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

    /**
     * Convertit une Entité Brand en BrandDTO
     */
    BrandDTO toDto(Brand brand);

    /**
     * Convertit un BrandDTO en Entité Brand
     * On ignore souvent models lors de la création d'une marque via DTO
     */
    @Mapping(target = "models", ignore = true)
    Brand toEntity(BrandDTO brandDTO);

    /**
     * Convertit une liste de Brand en liste de BrandDTO
     */
    List<BrandDTO> toDtoList(List<Brand> brands);
}