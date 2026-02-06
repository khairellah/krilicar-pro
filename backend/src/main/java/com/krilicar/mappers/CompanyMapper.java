package com.krilicar.mappers;

import com.krilicar.dtos.CompanyDTO;
import com.krilicar.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyDTO toDto(Company company);

    Company toEntity(CompanyDTO companyDTO);
}