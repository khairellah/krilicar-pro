package com.krilicar.services;

import com.krilicar.dtos.CarDTO;
import java.util.List;

public interface CarService {
    CarDTO saveCar(CarDTO carDTO);
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long id);
    List<CarDTO> getCarsByBrand(String brandName);
    CarDTO updateCar(Long id, CarDTO carDTO);
    void deleteCar(Long id);
}