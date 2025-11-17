package com.elena.companies_crud.api.dto;

import com.elena.companies_crud.domain.enums.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class WebSiteDTO implements Serializable {

    private Long id;
    @NotBlank
    private String name;
    private Category category;
    private String description;
}
