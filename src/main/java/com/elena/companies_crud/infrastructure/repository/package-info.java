/**
 * Contiene los <b>adaptadores de repositorio</b> de la capa de infraestructura.
 *
 * <p>Las clases de este paquete implementan los puertos de salida del dominio
 * definidos en {@code com.elena.companies_crud.domain.ports.out}, adaptando la
 * lógica de negocio a tecnologías de persistencia concretas.</p>
 *
 * <p>Ejemplo: {@code CompanyRepositoryAdapter}, que implementa
 * {@code CompanyRepositoryPort} delegando en un repositorio JPA.</p>
 */
package com.elena.companies_crud.infrastructure.repository;