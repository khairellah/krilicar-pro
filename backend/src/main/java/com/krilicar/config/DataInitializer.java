package com.krilicar.config;

import com.krilicar.entities.Admin;
import com.krilicar.enums.Role;
import com.krilicar.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final AdminRepository adminRepository;

    private static final String ADMIN_EMAIL = "admin@krili.com";
    private static final String DEFAULT_PASSWORD = "admin@2026";

    @Bean
    public CommandLineRunner initAdminData(PasswordEncoder passwordEncoder) {
        return args -> {
            // On vérifie si un compte avec cet email existe déjà
            if (adminRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {

                // On utilise SuperBuilder de l'entité Admin
                Admin admin = Admin.builder()
                        .firstName("Super")
                        .lastName("Admin")
                        .email(ADMIN_EMAIL)
                        .password(passwordEncoder.encode(DEFAULT_PASSWORD))
                        .role(Role.ADMIN)
                        .build();

                adminRepository.save(admin);
                System.out.println("✅ ADMIN INITIALISÉ : " + ADMIN_EMAIL);
            }
        };
    }
}