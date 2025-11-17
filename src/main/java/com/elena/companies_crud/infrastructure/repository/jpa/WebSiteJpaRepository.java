package com.elena.companies_crud.infrastructure.repository.jpa;

import com.elena.companies_crud.domain.model.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebSiteJpaRepository extends JpaRepository<WebSite, Long> {

    Optional<WebSite> findByName(String name);
}
