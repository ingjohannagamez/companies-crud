package com.elena.companies_crud.application.service;

import com.elena.companies_crud.common.exception.NotFoundException;
import com.elena.companies_crud.domain.model.WebSite;
import com.elena.companies_crud.domain.ports.in.WebSiteService;
import com.elena.companies_crud.domain.ports.out.WebSiteRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WebSiteServiceImpl implements WebSiteService {

    private final WebSiteRepositoryPort webSiteRepository;

    public WebSiteServiceImpl(WebSiteRepositoryPort webSiteRepository) {
        this.webSiteRepository = webSiteRepository;
    }

    @Override
    public WebSite createWebSite(WebSite webSite) {
        webSite.setId(null); // que lo genere la BD
        return webSiteRepository.save(webSite);
    }

    @Override
    @Transactional(readOnly = true)
    public WebSite getWebSiteById(Long id) {
        return webSiteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("WebSite not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public WebSite getWebSiteByName(String name) {
        return webSiteRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("WebSite not found with name: " + name));
    }

    @Override
    public WebSite updateWebSite(WebSite webSite) {
        if (webSite.getId() == null || !webSiteRepository.existsById(webSite.getId())) {
            throw new NotFoundException("WebSite not found: " + webSite.getId());
        }
        return webSiteRepository.save(webSite);
    }

    @Override
    public void deleteWebSite(Long id) {
        if (!webSiteRepository.existsById(id)) {
            throw new NotFoundException("WebSite not found: " + id);
        }
        webSiteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WebSite> getAllWebSites() {
        return webSiteRepository.findAll();
    }

}