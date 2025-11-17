/**
 * Contiene los <b>servicios de aplicación</b> de la arquitectura.
 *
 * <p>Las clases de este paquete implementan los
 * <i>puertos de entrada</i> definidos en
 * {@code com.elena.companies_crud.domain.ports.in} y representan
 * los casos de uso del sistema.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Orquestar la lógica de negocio del dominio.</li>
 *     <li>Coordinar el acceso a los puertos de salida.</li>
 *     <li>Lanzar excepciones de negocio cuando corresponda.</li>
 * </ul>
 *
 * <p>Las clases de este paquete no deben depender directamente de
 * detalles de infraestructura como JPA o controladores web.</p>
 */
package com.elena.companies_crud.application.service;