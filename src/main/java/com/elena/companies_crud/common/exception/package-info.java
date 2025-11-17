/**
 * Proporciona el <b>mecanismo común de manejo de excepciones</b> de la aplicación.
 *
 * <p>Incluye:</p>
 * <ul>
 *     <li>Excepciones de negocio como {@code NotFoundException}.</li>
 *     <li>La clase {@code GlobalExceptionHandler}, anotada con
 *     {@code @ControllerAdvice}, que centraliza el tratamiento de errores
 *     y construye respuestas HTTP consistentes.</li>
 * </ul>
 *
 * <p>El objetivo de este paquete es desacoplar el manejo de errores de la
 * lógica de negocio y ofrecer respuestas uniformes a los clientes de la API.</p>
 */
package com.elena.companies_crud.common.exception;