package com.elena.companies_crud.common.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ConstraintViolationException;

/**
 * Clase global para el manejo centralizado de excepciones en la aplicación.
 *
 * <p>Todos los métodos marcados con {@link ExceptionHandler} capturan tipos
 * específicos de excepciones y construyen una respuesta HTTP estándar con:
 * <ul>
 *     <li>timestamp: fecha y hora del error</li>
 *     <li>status: código de estado HTTP</li>
 *     <li>error: descripción corta del estado HTTP</li>
 *     <li>message: mensaje descriptivo del error</li>
 *     <li>path: información de la solicitud que originó la excepción</li>
 * </ul>
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja excepciones del tipo {@link ResponseStatusException}, que se usan
     * para propagar explícitamente un código de estado HTTP desde la lógica
     * de negocio o los controladores.
     *
     * @param ex      la excepción {@code ResponseStatusException} lanzada.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con el estado definido en la excepción y detalles del error.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        logger.error("ResponseStatusException: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", ex.getStatusCode().value());
        body.put("error", ((HttpStatus) ex.getStatusCode()).getReasonPhrase());
        body.put("message", "Error específico del estado de respuesta: " + ex.getReason());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, ex.getStatusCode());
    }

    /**
     * Maneja excepciones de tipo {@link MethodArgumentNotValidException},
     * que se producen cuando la validación de un {@code @RequestBody} o
     * parámetros anotados con {@code @Valid} falla.
     *
     * @param ex      la excepción de validación lanzada por Spring.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con estado 400 (Bad Request) y el detalle de los campos inválidos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        logger.error("Validation error: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing));

        body.put("message", "Errores de validación en los campos: " + errors);
        body.put("path", request.getDescription(false));
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Maneja excepciones del tipo {@link DataIntegrityViolationException},
     * que se generan cuando se viola una restricción de integridad en la base de datos
     * (por ejemplo, claves únicas, foráneas o restricciones de nulidad).
     *
     * @param ex      la excepción de violación de integridad de datos.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con estado 409 (Conflict) y detalles del problema de integridad.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        logger.error("Database error: {}", ex.getMostSpecificCause().getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        body.put("message", "Error de integridad en la base de datos: " + ex.getMostSpecificCause().getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * Maneja excepciones del tipo {@link ConstraintViolationException},
     * que se producen cuando fallan validaciones sobre parámetros de métodos
     * (por ejemplo, anotaciones de Bean Validation en parámetros de un controlador).
     *
     * @param ex      la excepción de violación de restricciones.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con estado 400 (Bad Request) y detalles de la violación de restricciones.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        logger.error("Constraint violation: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", "Violación de restricción: " + ex.getMessage());
        body.put("path", request.getDescription(false));
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Maneja excepciones del tipo {@link MultipartException},
     * que se producen cuando una solicitud esperada como multipart/form-data
     * no cumple el formato o presenta problemas en la carga de archivos.
     *
     * @param ex      la excepción {@code MultipartException} lanzada.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con estado 400 (Bad Request) y detalles del problema en la solicitud multipart.
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException ex, WebRequest request) {
        logger.error("Multipart request error: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", "La solicitud no es una solicitud multipart: " + ex.getMessage());
        body.put("path", request.getDescription(false));
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Maneja excepciones del tipo {@link NotFoundException}, las cuales se lanzan
     * cuando un recurso solicitado no existe en el sistema.
     *
     * <p>Devuelve una respuesta HTTP con estado <strong>404 Not Found</strong>,
     * incluyendo información detallada del error, como el mensaje descriptivo,
     * la ruta de la solicitud y la marca de tiempo.</p>
     *
     * @param ex      la excepción {@code NotFoundException} que fue lanzada.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con el estado 404 y detalles del recurso no encontrado.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.warn("Resource not found: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja cualquier otra excepción no controlada específicamente
     * por los manejadores anteriores.
     *
     * <p>Se utiliza como mecanismo de seguridad para capturar errores inesperados
     * y evitar que se propaguen sin control hacia el cliente.</p>
     *
     * @param ex      la excepción genérica lanzada.
     * @param request la solicitud web asociada al error.
     * @return una respuesta HTTP con estado 500 (Internal Server Error) y detalles del error inesperado.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put("message", "Error interno del servidor: " + ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}