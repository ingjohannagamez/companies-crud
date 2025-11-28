package com.elena.companies_crud.api.controller;

import com.elena.companies_crud.api.dto.CompanyDTO;
import com.elena.companies_crud.api.mapper.CompanyMapper;
import com.elena.companies_crud.domain.model.Company;
import com.elena.companies_crud.domain.ports.in.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@Tag(name = "Companies", description = "API para gestión de empresas")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(summary = "Crear empresa", description = "Crea una nueva empresa en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa creada exitosamente", content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CompanyDTO> create(
            @Parameter(description = "Datos de la empresa a crear") @Valid @RequestBody CompanyDTO dto) {
        Company created = companyService.createCompany(CompanyMapper.toEntity(dto));
        return ResponseEntity
                .created(URI.create("/api/companies/" + created.getId()))
                .body(CompanyMapper.toDto(created));
    }

    @Operation(summary = "Obtener empresa por ID", description = "Obtiene los detalles de una empresa específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada", content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getById(
            @Parameter(description = "ID de la empresa", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(
                CompanyMapper.toDto(companyService.readCompanyById(id)));
    }

    @Operation(summary = "Obtener empresa por nombre", description = "Busca una empresa por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada", content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content)
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<CompanyDTO> getByName(
            @Parameter(description = "Nombre de la empresa", example = "TechCorp") @PathVariable String name) {
        return ResponseEntity.ok(
                CompanyMapper.toDto(companyService.readCompanyByName(name)));
    }

    @Operation(summary = "Actualizar empresa", description = "Actualiza los datos de una empresa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada exitosamente", content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(
            @Parameter(description = "ID de la empresa", example = "1") @PathVariable Long id,
            @Parameter(description = "Datos actualizados de la empresa") @Valid @RequestBody CompanyDTO dto) {
        dto.setId(id);
        Company updated = companyService.updateCompany(CompanyMapper.toEntity(dto));
        return ResponseEntity.ok(CompanyMapper.toDto(updated));
    }

    @Operation(summary = "Eliminar empresa", description = "Elimina una empresa del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la empresa a eliminar", example = "1") @PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todas las empresas", description = "Obtiene la lista completa de empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {
        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompanies()));
    }

    @Operation(summary = "Buscar empresas por logo", description = "Obtiene empresas filtradas por logo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    })
    @GetMapping("/logo/{logo}")
    public ResponseEntity<List<CompanyDTO>> getByLogo(
            @Parameter(description = "Logo de la empresa", example = "logo.png") @PathVariable String logo) {
        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByLogo(logo)));
    }

    @Operation(summary = "Buscar empresas por fecha de fundación", description = "Obtiene empresas fundadas en una fecha específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    })
    @GetMapping("/foundation-date/{date}")
    public ResponseEntity<List<CompanyDTO>> getByFoundationDate(
            @Parameter(description = "Fecha de fundación (formato: dd/MM/yyyy)", example = "01/01/2020") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByFoundationDate(date)));
    }

    @Operation(summary = "Buscar empresas fundadas después de una fecha", description = "Obtiene empresas fundadas después de la fecha especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    })
    @GetMapping("/foundation-date/after/{date}")
    public ResponseEntity<List<CompanyDTO>> getByFoundationDateAfter(
            @Parameter(description = "Fecha de referencia (formato: dd/MM/yyyy)", example = "01/01/2020") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByFoundationDateAfter(date)));
    }

    @Operation(summary = "Buscar empresas fundadas antes de una fecha", description = "Obtiene empresas fundadas antes de la fecha especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    })
    @GetMapping("/foundation-date/before/{date}")
    public ResponseEntity<List<CompanyDTO>> getByFoundationDateBefore(
            @Parameter(description = "Fecha de referencia (formato: dd/MM/yyyy)", example = "01/01/2020") @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                CompanyMapper.toDtoList(companyService.getCompaniesByFoundationDateBefore(date)));
    }

}
