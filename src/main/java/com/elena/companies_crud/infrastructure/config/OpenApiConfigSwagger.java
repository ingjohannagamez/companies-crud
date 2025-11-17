package com.elena.companies_crud.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Companies CRUD API",
                version = "v1",
                description = "API REST para la gestión de compañías y sitios web (Clean Architecture / Hexagonal).",
                contact = @Contact(
                        name = "Johann Andrés Agamez Ferres",
                        email = "ingjohannagamez@gmail.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Servidor local"
                )
        }
)
public class OpenApiConfigSwagger {

    /**
     * Configuración principal de OpenAPI: metadatos generales.
     */
    @Bean
    public OpenAPI companiesCrudOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Companies CRUD API")
                        .version("v1")
                        .description("API REST para gestionar Companies y WebSites siguiendo principios de Arquitectura Limpia.")
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub – companies-crud")
                        .url("https://github.com/ingjohannagamez/companies-crud"));
    }

    /**
     * Grupo de endpoints relacionados con Companies.
     * Se agrupan todas las rutas que empiezan por /api/companies.
     */
    @Bean
    public GroupedOpenApi companiesGroup() {
        return GroupedOpenApi.builder()
                .group("companies")
                .pathsToMatch("/api/companies/**")
                .build();
    }

    /**
     * Grupo de endpoints relacionados con WebSites.
     * Se agrupan todas las rutas que empiezan por /api/websites.
     */
    @Bean
    public GroupedOpenApi webSitesGroup() {
        return GroupedOpenApi.builder()
                .group("websites")
                .pathsToMatch("/api/websites/**")
                .build();
    }

}