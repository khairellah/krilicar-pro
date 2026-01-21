package com.krilicar.services;

import com.krilicar.dtos.BrandDTO;
import java.util.List;

public interface BrandService {
    BrandDTO saveBrand(BrandDTO brandDTO);
    List<BrandDTO> getAllBrands();
    BrandDTO getBrandById(Long id);
    BrandDTO updateBrand(Long id, BrandDTO brandDTO);
    void deleteBrand(Long id);
    boolean existsByName(String name);
}