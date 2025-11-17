package com.elena.companies_crud.domain.ports.in;

import com.elena.companies_crud.domain.model.WebSite;

import java.util.List;

public interface WebSiteService {

    WebSite createWebSite(WebSite webSite);
    WebSite getWebSiteById(Long id);
    WebSite getWebSiteByName(String name);
    WebSite updateWebSite(WebSite webSite);
    void deleteWebSite(Long id);
    List<WebSite> getAllWebSites();

}
