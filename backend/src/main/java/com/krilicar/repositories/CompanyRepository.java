package com.krilicar.repositories;

import com.krilicar.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // On peut chercher par l'email hérité de AppUser
    Optional<Company> findByEmailIgnoreCase(String email);

    // On peut aussi chercher par le nom (lastName dans AppUser)
    Optional<Company> findByLastNameIgnoreCase(String lastName);
}