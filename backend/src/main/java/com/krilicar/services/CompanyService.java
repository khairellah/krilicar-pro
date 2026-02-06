package com.krilicar.services;

import com.krilicar.dtos.CompanyDTO;
import java.util.List;

public interface CompanyService {
    CompanyDTO saveCompany(CompanyDTO companyDTO);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO getCompanyById(Long id);
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    void deleteCompany(Long id);
}