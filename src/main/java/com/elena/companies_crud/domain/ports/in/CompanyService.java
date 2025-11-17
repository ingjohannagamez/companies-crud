package com.elena.companies_crud.domain.ports.in;

import com.elena.companies_crud.domain.model.Company;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {

    Company createCompany(Company company);
    Company readCompanyById(Long id);
    Company readCompanyByName(String name);
    Company updateCompany(Company company);
    void deleteCompany(Long id);
    List<Company> getCompanies();
    List<Company> getCompaniesByLogo(String logo);
    List<Company> getCompaniesByFoundationDate(LocalDate foundationDate);
    List<Company> getCompaniesByFoundationDateAfter(LocalDate foundationDate);
    List<Company> getCompaniesByFoundationDateBefore(LocalDate foundationDate);

}
