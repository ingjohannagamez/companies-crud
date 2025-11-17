package com.elena.companies_crud.api.controller;

import com.elena.companies_crud.api.dto.CompanyDTO;
import com.elena.companies_crud.api.mapper.CompanyMapper;
import com.elena.companies_crud.domain.model.Company;
import com.elena.companies_crud.domain.ports.in.CompanyService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@Valid @RequestBody CompanyDTO dto) {
        Company created = companyService.createCompany(CompanyMapper.toEntity(dto));
        return ResponseEntity
                .created(URI.create("/api/companies/" + created.getId()))
                .body(CompanyMapper.toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                CompanyMapper.toDto(companyService.readCompanyById(id))
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CompanyDTO> getByName(@PathVariable String name) {
        return ResponseEntity.ok(
                CompanyMapper.toDto(companyService.readCompanyByName(name))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable Long id,
                                             @Valid @RequestBody CompanyDTO dto) {
        dto.setId(id);
        Company updated = companyService.updateCompany(CompanyMapper.toEntity(dto));
        return ResponseEntity.ok(CompanyMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {
        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompanies())
        );
    }

    @GetMapping("/logo/{logo}")
    public ResponseEntity<List<CompanyDTO>> getByLogo(@PathVariable String logo) {
        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByLogo(logo))
        );
    }

    @GetMapping("/foundation-date/{date}")
    public ResponseEntity<List<CompanyDTO>> getByFoundationDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByFoundationDate(date))
        );
    }

    @GetMapping("/foundation-date/after/{date}")
    public ResponseEntity<List<CompanyDTO>> getByFoundationDateAfter(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByFoundationDateAfter(date))
        );
    }

    @GetMapping("/foundation-date/before/{date}")
    public ResponseEntity<List<CompanyDTO>> getByFoundationDateBefore(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByFoundationDateBefore(date))
        );
    }

}
