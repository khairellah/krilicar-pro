package com.krilicar.services;

import com.krilicar.dtos.CarDTO;
import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
}