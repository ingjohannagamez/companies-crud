package com.elena.companies_crud.infrastructure.repository;

import com.elena.companies_crud.domain.model.WebSite;
import com.elena.companies_crud.domain.ports.out.WebSiteRepositoryPort;
import com.elena.companies_crud.infrastructure.repository.jpa.WebSiteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WebSiteRepositoryAdapter implements WebSiteRepositoryPort {

    private final WebSiteJpaRepository webSiteJpaRepository;

    public WebSiteRepositoryAdapter(WebSiteJpaRepository webSiteJpaRepository) {
        this.webSiteJpaRepository = webSiteJpaRepository;
    }

    @Override
    public WebSite save(WebSite webSite) {
        return webSiteJpaRepository.save(webSite);
    }

    @Override
    public Optional<WebSite> findById(Long id) {
        return webSiteJpaRepository.findById(id);
    }

    @Override
    public Optional<WebSite> findByName(String name) {
        return webSiteJpaRepository.findByName(name);
    }

    @Override
    public List<WebSite> findAll() {
        return webSiteJpaRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return webSiteJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        webSiteJpaRepository.deleteById(id);
    }

}