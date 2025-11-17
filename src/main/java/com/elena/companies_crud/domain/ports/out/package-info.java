/**
 * Define los <b>puertos de salida</b> del dominio.
 *
 * <p>Los puertos de salida representan las dependencias del dominio hacia
 * sistemas externos, como repositorios de datos u otros servicios.</p>
 *
 * <p>Ejemplo: {@code CompanyRepositoryPort}, que abstrae las operaciones
 * de persistencia de compañías.</p>
 *
 * <p>Los puertos de salida son implementados por adaptadores de
 * infraestructura en el paquete
 * {@code com.elena.companies_crud.infrastructure.repository}.</p>
 */
package com.elena.companies_crud.domain.ports.out;