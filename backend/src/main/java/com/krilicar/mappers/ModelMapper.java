package com.krilicar.mappers;

import com.krilicar.dtos.ModelDTO;
import com.krilicar.entities.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    @Mapping(source = "brand.id", target = "brandId")
    ModelDTO toDto(Model model);

    @Mapping(source = "brandId", target = "brand.id")
    Model toEntity(ModelDTO modelDTO);

    List<ModelDTO> toDtoList(List<Model> models);
}