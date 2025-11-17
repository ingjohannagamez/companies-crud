package com.elena.companies_crud.api.mapper;

import com.elena.companies_crud.api.dto.WebSiteDTO;
import com.elena.companies_crud.domain.model.WebSite;

import java.util.List;
import java.util.stream.Collectors;

public final class WebSiteMapper {

    private WebSiteMapper() {
        // util class
    }

    @SuppressWarnings("DuplicatedCode")
    public static WebSiteDTO toDto(WebSite webSite) {
        if (webSite == null) {
            return null;
        }
        WebSiteDTO dto = new WebSiteDTO();
        dto.setId(webSite.getId());
        dto.setName(webSite.getName());
        dto.setCategory(webSite.getCategory());
        dto.setDescription(webSite.getDescription());
        return dto;
    }

    @SuppressWarnings("DuplicatedCode")
    public static WebSite toEntity(WebSiteDTO dto) {
        if (dto == null) {
            return null;
        }
        WebSite webSite = new WebSite();
        webSite.setId(dto.getId());
        webSite.setName(dto.getName());
        webSite.setCategory(dto.getCategory());
        webSite.setDescription(dto.getDescription());
        return webSite;
    }

    public static List<WebSiteDTO> toDtoList(List<WebSite> entities) {
        return entities.stream()
                .map(WebSiteMapper::toDto)
                .collect(Collectors.toList());
    }

}
