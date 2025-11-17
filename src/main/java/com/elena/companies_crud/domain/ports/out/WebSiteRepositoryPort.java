package com.elena.companies_crud.domain.ports.out;

import com.elena.companies_crud.domain.model.WebSite;

import java.util.List;
import java.util.Optional;

public interface WebSiteRepositoryPort {

    WebSite save(WebSite webSite);
    Optional<WebSite> findById(Long id);
    Optional<WebSite> findByName(String name);
    List<WebSite> findAll();
    boolean existsById(Long id);
    void deleteById(Long id);

}
