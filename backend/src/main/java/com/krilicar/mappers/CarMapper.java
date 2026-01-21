package com.krilicar.mappers;

import com.krilicar.dtos.CarDTO;
import com.krilicar.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(source = "model.name", target = "modelName")
    CarDTO toDto(Car car);

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "model", ignore = true)
    Car toEntity(CarDTO carDTO);
}