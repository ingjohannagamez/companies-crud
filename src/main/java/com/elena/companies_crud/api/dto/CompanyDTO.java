package com.elena.companies_crud.api.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class CompanyDTO implements Serializable {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String founder;
    private String logo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;
    private List<WebSiteDTO> webSites;

}