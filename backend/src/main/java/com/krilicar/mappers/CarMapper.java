package com.krilicar.mappers;

import com.krilicar.dtos.CarDTO;
import com.krilicar.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    // On récupère le nom de la marque à travers le modèle
    @Mapping(source = "model.brand.name", target = "brandName")
    @Mapping(source = "model.name", target = "modelName")
    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "company.id", target = "companyId")
    CarDTO toDto(Car car);

    // Pour la création, on ignore les objets complets car le Service
    // s'occupera de les charger via leurs IDs
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "company", ignore = true)
    Car toEntity(CarDTO carDTO);
}