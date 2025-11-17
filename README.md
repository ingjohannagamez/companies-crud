# 🚀 Companies CRUD – Spring Boot + Clean Architecture

Este proyecto implementa un sistema CRUD para la gestión de compañías (**companies**) utilizando **Spring Boot**, **PostgreSQL**, **Docker** y principios de **Arquitectura Limpia (Clean Architecture / Hexagonal)**.

Su diseño está orientado a lograr:

- Alta mantenibilidad
- Bajo acoplamiento
- Código escalable para nuevos módulos
- Separación clara entre dominio, infraestructura y aplicación

--------------------------------------------------
# 📂 Estructura de Paquetes

src/main/java/com/elena/companies_crud
 ├── application
 │   └── service
 │        └── CompanyServiceImpl.java
 │
 ├── common
 │   └── exception
 │        ├── GlobalExceptionHandler.java
 │        └── NotFoundException.java
 │
 ├── domain
 │   ├── enums
 │   │    └── CompanyType.java
 │   ├── model
 │   │    └── Company.java
 │   └── ports
 │        ├── in
 │        │    └── CompanyService.java
 │        └── out
 │             └── CompanyRepositoryPort.java
 │
 ├── infrastructure
 │   ├── repository
 │   │    ├── CompanyRepositoryAdapter.java
 │   │    └── jpa
 │   │         └── CompanyJpaRepository.java
 │   └── config
 │        └── OpenApiConfig.java
 │
 └── CompaniesCrudApplication.java

--------------------------------------------------
# 🧱 Arquitectura

La arquitectura sigue los principios de **Clean Architecture / Hexagonal (Ports & Adapters)**:

- El dominio no conoce detalles de infraestructura (Spring, JPA, HTTP, etc.).
- La aplicación orquesta casos de uso usando puertos del dominio.
- La infraestructura implementa los puertos de salida y se conecta a la base de datos.
- La API (controladores) sólo habla con casos de uso (puertos de entrada).

## Dominio
- Contiene la lógica empresarial central y las reglas de negocio.
- Modelos:
  - `Company`
- Enums:
  - `CompanyType`
- Puertos:
  - Entrada (IN):
    - `CompanyService`
  - Salida (OUT):
    - `CompanyRepositoryPort`

## Application Service (Casos de uso)
- Implementa los puertos de entrada del dominio.
- Clase principal:
  - `CompanyServiceImpl`
- Responsabilidades:
  - Orquestar la lógica de negocio.
  - Validar flujos de actualización, creación y borrado.
  - Lanzar excepciones de negocio (`NotFoundException`, etc.).
- No depende de:
  - JPA
  - Detalles de la base de datos
  - Clases de infraestructura

## Infrastructure (Adaptadores)
- Implementa los puertos de salida del dominio.
- Clases:
  - `CompanyRepositoryAdapter`
    - Implementa `CompanyRepositoryPort`.
    - Adapta llamadas del dominio a Spring Data JPA.
  - `CompanyJpaRepository`
    - Extiende `JpaRepository<Company, Long>`.
    - Expone métodos como `findByName`, `findByLogo`, `findByFoundationDate`, etc.
  - `OpenApiConfig`
    - Configuración de Swagger / OpenAPI.

## Common (Errores y Excepciones)
- `GlobalExceptionHandler`
  - Manejo centralizado de excepciones con `@ControllerAdvice`.
  - Devuelve respuestas JSON consistentes con timestamp, status, error, message y path.
- `NotFoundException`
  - Excepción de negocio para recursos no encontrados (404).

--------------------------------------------------
# 🛠 Tecnologías Utilizadas

- **Java 25**
- **Spring Boot 4 (4.0.0-SNAPSHOT)**
- **Spring Web (REST)**
- **Spring Data JPA**
- **PostgreSQL 16**
- **Docker & Docker Compose**
- **OpenAPI 3 / Swagger UI** (springdoc-openapi-starter-webmvc-ui)
- **Maven**
- **IntelliJ IDEA**

--------------------------------------------------
# 🐳 Docker – Base de Datos PostgreSQL

El proyecto incluye un `docker-compose.yml` para levantar una instancia de PostgreSQL:

Ejemplo (adaptar a tu archivo real):

services:
  db-companies:
    image: postgres:16-alpine
    container_name: db-companies
    environment:
      POSTGRES_DB: companies
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - db_companies_data:/var/lib/postgresql/data
    restart: unless-stopped

volumes:
  db_companies_data:

Para levantar la base de datos:

docker compose up -d

La base de datos estará disponible en:

- Host: `localhost`
- Puerto: `5432`
- Base de datos: `companies`
- Usuario: `postgres`
- Password: `postgres` (o la que hayas configurado)

--------------------------------------------------
# ⚙️ Configuración de la Aplicación

Archivo: `src/main/resources/application.properties`

Ejemplo de configuración (adaptar a la tuya):

spring.application.name=companies-crud
server.port=8080

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/companies
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Swagger / OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# Logging
logging.level.root=INFO
logging.file.name=logs/companies-crud.log

--------------------------------------------------
# 📘 Documentación API (Swagger)

Una vez que la aplicación esté levantada, la documentación de la API estará disponible en:

- Swagger UI:
  - http://localhost:8080/swagger-ui.html
  - o http://localhost:8080/swagger-ui/index.html

- OpenAPI JSON:
  - http://localhost:8080/v3/api-docs

--------------------------------------------------
# 🔁 Endpoints Principales (Ejemplo)

Dependiendo de cómo tengas implementado tu `CompanyController`, los endpoints típicos serían:

| Método | Endpoint                  | Descripción                        |
|--------|---------------------------|------------------------------------|
| GET    | `/api/companies`          | Listar todas las compañías         |
| GET    | `/api/companies/{id}`     | Obtener compañía por ID            |
| GET    | `/api/companies/name/{name}` | Obtener compañía por nombre    |
| POST   | `/api/companies`          | Crear una nueva compañía           |
| PUT    | `/api/companies/{id}`     | Actualizar una compañía existente  |
| DELETE | `/api/companies/{id}`     | Eliminar una compañía por ID       |

(Adaptar estos endpoints a la firma real de tu controlador.)

--------------------------------------------------
# 🧪 Ejecución del Proyecto

1️⃣ Construir el proyecto:

mvn clean install

2️⃣ Ejecutar la aplicación:

mvn spring-boot:run

3️⃣ Verificar que está arriba (health check):

http://localhost:8080/actuator/health

Deberías recibir una respuesta similar a:

{"status":"UP"}

--------------------------------------------------
# 🧩 Extensibilidad

Gracias a la separación en capas y al uso de puertos (IN/OUT), el proyecto es fácil de extender:

- Nuevas entidades de dominio (Employee, Website, Billing, etc.)
- Nuevos adaptadores de salida (Kafka, RabbitMQ, otros motores de base de datos)
- Nuevas interfaces de entrada (REST, GraphQL, mensajería)

Todo esto se puede agregar sin romper el dominio actual.

--------------------------------------------------
# 📜 Autor

Proyecto desarrollado por **Johann Andrés Agamez Ferres** como ejercicio de arquitectura limpia con Spring Boot y PostgreSQL.

