package com.krilicar.repositories;

import com.krilicar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrandId(Long brandId);
    Optional<Model> findByNameIgnoreCaseAndBrandId(String name, Long brandId);
}