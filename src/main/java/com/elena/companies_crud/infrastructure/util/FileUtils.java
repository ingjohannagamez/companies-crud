package com.elena.companies_crud.infrastructure.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para operaciones de carga y validación de archivos.
 * Forma parte de la capa de infraestructura debido a que
 * interactúa con el sistema de archivos.
 */
public class FileUtils {

    private static final String UPLOADS_DIR = "uploads/";

    /**
     * Valida que el archivo tenga un nombre válido y que su extensión sea .txt.
     *
     * @param file MultipartFile cargado en la solicitud.
     */
    public static void validarArchivoTxt(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El archivo debe tener un nombre válido");
        }

        if (!originalFilename.toLowerCase().endsWith(".txt")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El archivo debe ser un archivo .txt");
        }
    }

    /**
     * Crea el directorio de carga si no existe.
     */
    public static void crearDirectorioDeCargaSiNoExiste() {
        Path uploadsPath = Paths.get(UPLOADS_DIR);
        if (!Files.exists(uploadsPath)) {
            try {
                Files.createDirectories(uploadsPath);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al crear el directorio de carga");
            }
        }
    }

    /**
     * Genera un nombre único basado en timestamp.
     *
     * @param originalFilename nombre original del archivo.
     * @return nombre único generado.
     */
    public static String generarNombreDeArchivoUnico(String originalFilename) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String baseName = originalFilename.replace(".txt", "");
        return baseName + "_" + timestamp + ".txt";
    }

    /**
     * Guarda un archivo en el servidor en la carpeta /uploads.
     *
     * @param file          archivo recibido en la petición.
     * @param nombreArchivo nombre único generado.
     */
    public static void guardarArchivo(MultipartFile file, String nombreArchivo) {
        Path filePath = Paths.get(UPLOADS_DIR, nombreArchivo);

        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el archivo");
        }
    }

}
