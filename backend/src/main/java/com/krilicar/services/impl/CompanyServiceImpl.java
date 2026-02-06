package com.krilicar.services.impl;

import com.krilicar.dtos.CompanyDTO;
import com.krilicar.entities.Company;
import com.krilicar.enums.Role;
import com.krilicar.exceptions.DuplicateResourceException;
import com.krilicar.exceptions.ResourceNotFoundException;
import com.krilicar.mappers.CompanyMapper;
import com.krilicar.repositories.CompanyRepository;
import com.krilicar.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        // 1. Vérifier si l'email existe déjà (clé unique héritée de AppUser)
        if (companyRepository.findByEmailIgnoreCase(companyDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Agence", "email", companyDTO.getEmail());
        }

        // 2. Mapper le DTO en Entité
        Company company = companyMapper.toEntity(companyDTO);

        // 3. Configuration de la sécurité
        company.setRole(Role.COMPANY);

        // Hachage du mot de passe (on utilise "123456" par défaut si non fourni)
        String rawPassword = (company.getPassword() != null && !company.getPassword().isEmpty())
                ? company.getPassword()
                : "123456";
        company.setPassword(passwordEncoder.encode(rawPassword));

        // 4. Sauvegarde
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDto(savedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDTO getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", "id", id));
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", "id", id));

        // Mise à jour des champs (on ne change pas l'email ni le mot de passe ici par sécurité)
        existing.setLastName(companyDTO.getLastName());
        existing.setPhone(companyDTO.getPhone());
        existing.setLandline(companyDTO.getLandline());
        existing.setCity(companyDTO.getCity());
        existing.setDescription(companyDTO.getDescription());
        existing.setIsBooster(companyDTO.getIsBooster());

        return companyMapper.toDto(companyRepository.save(existing));
    }

    @Override
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", "id", id);
        }
        companyRepository.deleteById(id);
    }
}