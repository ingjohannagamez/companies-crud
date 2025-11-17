package com.elena.companies_crud.api.controller;

import com.elena.companies_crud.api.dto.WebSiteDTO;
import com.elena.companies_crud.api.mapper.WebSiteMapper;
import com.elena.companies_crud.domain.model.WebSite;
import com.elena.companies_crud.domain.ports.in.WebSiteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/websites")
public class WebSiteController {

    private final WebSiteService webSiteService;

    public WebSiteController(WebSiteService webSiteService) {
        this.webSiteService = webSiteService;
    }

    @PostMapping
    public ResponseEntity<WebSiteDTO> create(@Valid @RequestBody WebSiteDTO webSiteDTO) {
        WebSite webSite = WebSiteMapper.toEntity(webSiteDTO);
        WebSite created = webSiteService.createWebSite(webSite);
        WebSiteDTO response = WebSiteMapper.toDto(created);
        return ResponseEntity
                .created(URI.create("/api/websites/" + response.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebSiteDTO> getById(@PathVariable Long id) {
        WebSite webSite = webSiteService.getWebSiteById(id);
        return ResponseEntity.ok(WebSiteMapper.toDto(webSite));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<WebSiteDTO> getByName(@PathVariable String name) {
        WebSite webSite = webSiteService.getWebSiteByName(name);
        return ResponseEntity.ok(WebSiteMapper.toDto(webSite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebSiteDTO> update(@PathVariable Long id,
                                             @Valid @RequestBody WebSiteDTO webSiteDTO) {
        // aseguramos que el ID del path manda
        webSiteDTO.setId(id);
        WebSite webSite = WebSiteMapper.toEntity(webSiteDTO);
        WebSite updated = webSiteService.updateWebSite(webSite);
        return ResponseEntity.ok(WebSiteMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        webSiteService.deleteWebSite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WebSiteDTO>> getAll() {
        List<WebSite> webSites = webSiteService.getAllWebSites();
        return ResponseEntity.ok(WebSiteMapper.toDtoList(webSites));
    }

}
