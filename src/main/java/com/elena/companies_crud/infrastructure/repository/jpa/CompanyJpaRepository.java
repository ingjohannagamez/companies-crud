package com.elena.companies_crud.infrastructure.repository.jpa;

import com.elena.companies_crud.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {

        Optional<Company> findByName(String name);
        List<Company> findByLogo(String logo);
        List<Company> findByFoundationDate(LocalDate foundationDate);
        List<Company> findByFoundationDateAfter(LocalDate date);
        List<Company> findByFoundationDateBefore(LocalDate date);

}
