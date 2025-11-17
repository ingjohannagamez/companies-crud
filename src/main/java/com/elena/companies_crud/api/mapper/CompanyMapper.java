package com.elena.companies_crud.api.mapper;

import com.elena.companies_crud.api.dto.CompanyDTO;
import com.elena.companies_crud.domain.model.Company;

import java.util.List;
import java.util.stream.Collectors;

public final class CompanyMapper {

    private CompanyMapper() {}

    @SuppressWarnings("DuplicatedCode")
    public static CompanyDTO toDto(Company company) {
        if (company == null) return null;

        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setFounder(company.getFounder());
        dto.setLogo(company.getLogo());
        dto.setFoundationDate(company.getFoundationDate());

        if (company.getWebSites() != null) {
            dto.setWebSites(
                    company.getWebSites().stream()
                            .map(WebSiteMapper::toDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    @SuppressWarnings("DuplicatedCode")
    public static Company toEntity(CompanyDTO dto) {
        if (dto == null) return null;

        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setFounder(dto.getFounder());
        company.setLogo(dto.getLogo());
        company.setFoundationDate(dto.getFoundationDate());

        if (dto.getWebSites() != null) {
            company.setWebSites(
                    dto.getWebSites().stream()
                            .map(WebSiteMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }

        return company;
    }

    public static List<CompanyDTO> toDtoList(List<Company> companies) {
        return companies.stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }

}