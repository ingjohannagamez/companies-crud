package com.elena.companies_crud.domain.ports.out;

import com.elena.companies_crud.domain.model.Company;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryPort {

    Company save(Company company);
    Optional<Company> findById(Long id);
    Optional<Company> findByName(String name);
    List<Company> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Company> findByLogo(String logo);
    List<Company> findByFoundationDate(LocalDate foundationDate);
    List<Company> findByFoundationDateAfter(LocalDate date);
    List<Company> findByFoundationDateBefore(LocalDate date);

}
