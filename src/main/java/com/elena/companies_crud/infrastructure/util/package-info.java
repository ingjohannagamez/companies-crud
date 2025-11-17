/**
 * Utilidades específicas de la capa de infraestructura.
 *
 * <p>Este paquete contiene clases auxiliares que dependen directamente
 * de tecnologías externas o mecanismos operativos del sistema, tales como:
 * manejo del sistema de archivos, acceso a recursos físicos, validación y
 * procesamiento de archivos, o cualquier funcionalidad que requiera interacción
 * con el entorno técnico donde se ejecuta la aplicación.</p>
 *
 * <p>En el contexto de Arquitectura Limpia (Clean Architecture / Hexagonal),
 * estas utilidades deben permanecer exclusivamente en infraestructura, ya que
 * no pertenecen al dominio y no deben filtrarse hacia las capas de aplicación
 * o API. Esto garantiza que la lógica de negocio permanezca aislada de
 * dependencias tecnológicas y evita acoplamiento indeseado.</p>
 *
 * <p>Ejemplos típicos que se ubican aquí incluyen:</p>
 * <ul>
 *     <li>Manejo de archivos y directorios (escritura, lectura, validación).</li>
 *     <li>Conversión o adaptación de recursos externos.</li>
 *     <li>Utilidades basadas en frameworks (Spring, JPA, etc.).</li>
 * </ul>
 *
 * <p>En esta implementación, la clase {@code FileUtils} es un ejemplo concreto
 * de funcionalidad de infraestructura, ya que gestiona validación, generación
 * de nombres únicos y almacenamiento físico de archivos dentro de la carpeta
 * "uploads/" del proyecto.</p>
 */
package com.elena.companies_crud.infrastructure.util;