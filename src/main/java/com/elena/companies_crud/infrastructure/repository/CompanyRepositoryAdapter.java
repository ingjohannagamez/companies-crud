package com.elena.companies_crud.infrastructure.repository;

import com.elena.companies_crud.domain.model.Company;
import com.elena.companies_crud.domain.ports.out.CompanyRepositoryPort;
import com.elena.companies_crud.infrastructure.repository.jpa.CompanyJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {

    private final CompanyJpaRepository jpaRepository;

    public CompanyRepositoryAdapter(CompanyJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Company save(Company company) {
        return jpaRepository.save(company);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Company> findByName(String name) {
        return jpaRepository.findByName(name);
    }

    @Override
    public List<Company> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<Company> findByLogo(String logo) {
        return jpaRepository.findByLogo(logo);
    }

    @Override
    public List<Company> findByFoundationDate(LocalDate foundationDate) {
        return jpaRepository.findByFoundationDate(foundationDate);
    }

    @Override
    public List<Company> findByFoundationDateAfter(LocalDate foundationDate) {
        return jpaRepository.findByFoundationDateAfter(foundationDate);
    }

    @Override
    public List<Company> findByFoundationDateBefore(LocalDate foundationDate) {
        return jpaRepository.findByFoundationDateBefore(foundationDate);
    }

}
