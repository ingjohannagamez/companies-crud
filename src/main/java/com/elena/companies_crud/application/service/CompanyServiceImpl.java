package com.elena.companies_crud.application.service;

import com.elena.companies_crud.common.exception.NotFoundException;
import com.elena.companies_crud.domain.model.Company;
import com.elena.companies_crud.domain.ports.in.CompanyService;
import com.elena.companies_crud.domain.ports.out.CompanyRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryPort companyRepository;

    public CompanyServiceImpl(CompanyRepositoryPort companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(Company company) {
        company.setId(null); // que lo genere la BD
        return companyRepository.save(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Company readCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Company readCompanyByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Company not found with name: " + name));
    }

    @Override
    public Company updateCompany(Company company) {
        if (company.getId() == null || !companyRepository.existsById(company.getId())) {
            throw new NotFoundException("Company not found: " + company.getId());
        }
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException("Company not found: " + id);
        }
        companyRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getCompaniesByLogo(String logo) {
        return companyRepository.findByLogo(logo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getCompaniesByFoundationDate(LocalDate foundationDate) {
        return companyRepository.findByFoundationDate(foundationDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getCompaniesByFoundationDateAfter(LocalDate foundationDate) {
        return companyRepository.findByFoundationDateAfter(foundationDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getCompaniesByFoundationDateBefore(LocalDate foundationDate) {
        return companyRepository.findByFoundationDateBefore(foundationDate);
    }

}
