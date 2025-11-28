package com.elena.companies_crud.api.controller;

import com.elena.companies_crud.api.dto.WebSiteDTO;
import com.elena.companies_crud.api.mapper.WebSiteMapper;
import com.elena.companies_crud.domain.model.WebSite;
import com.elena.companies_crud.domain.ports.in.WebSiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/websites")
@Tag(name = "Web Sites", description = "API para gestión de sitios web de empresas")
public class WebSiteController {

    private final WebSiteService webSiteService;

    public WebSiteController(WebSiteService webSiteService) {
        this.webSiteService = webSiteService;
    }

    @Operation(summary = "Crear sitio web", description = "Crea un nuevo sitio web asociado a una empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sitio web creado exitosamente", content = @Content(schema = @Schema(implementation = WebSiteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<WebSiteDTO> create(
            @Parameter(description = "Datos del sitio web a crear") @Valid @RequestBody WebSiteDTO webSiteDTO) {
        WebSite webSite = WebSiteMapper.toEntity(webSiteDTO);
        WebSite created = webSiteService.createWebSite(webSite);
        WebSiteDTO response = WebSiteMapper.toDto(created);
        return ResponseEntity
                .created(URI.create("/api/websites/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "Obtener sitio web por ID", description = "Obtiene los detalles de un sitio web específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sitio web encontrado", content = @Content(schema = @Schema(implementation = WebSiteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sitio web no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<WebSiteDTO> getById(
            @Parameter(description = "ID del sitio web", example = "1") @PathVariable Long id) {
        WebSite webSite = webSiteService.getWebSiteById(id);
        return ResponseEntity.ok(WebSiteMapper.toDto(webSite));
    }

    @Operation(summary = "Obtener sitio web por nombre", description = "Busca un sitio web por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sitio web encontrado", content = @Content(schema = @Schema(implementation = WebSiteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Sitio web no encontrado", content = @Content)
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<WebSiteDTO> getByName(
            @Parameter(description = "Nombre del sitio web", example = "Portal Principal") @PathVariable String name) {
        WebSite webSite = webSiteService.getWebSiteByName(name);
        return ResponseEntity.ok(WebSiteMapper.toDto(webSite));
    }

    @Operation(summary = "Actualizar sitio web", description = "Actualiza los datos de un sitio web existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sitio web actualizado exitosamente", content = @Content(schema = @Schema(implementation = WebSiteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Sitio web no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<WebSiteDTO> update(
            @Parameter(description = "ID del sitio web", example = "1") @PathVariable Long id,
            @Parameter(description = "Datos actualizados del sitio web") @Valid @RequestBody WebSiteDTO webSiteDTO) {
        // aseguramos que el ID del path manda
        webSiteDTO.setId(id);
        WebSite webSite = WebSiteMapper.toEntity(webSiteDTO);
        WebSite updated = webSiteService.updateWebSite(webSite);
        return ResponseEntity.ok(WebSiteMapper.toDto(updated));
    }

    @Operation(summary = "Eliminar sitio web", description = "Elimina un sitio web del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sitio web eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sitio web no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del sitio web a eliminar", example = "1") @PathVariable Long id) {
        webSiteService.deleteWebSite(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todos los sitios web", description = "Obtiene la lista completa de sitios web")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sitios web obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<WebSiteDTO>> getAll() {
        List<WebSite> webSites = webSiteService.getAllWebSites();
        return ResponseEntity.ok(WebSiteMapper.toDtoList(webSites));
    }

}
