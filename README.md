# Plazoleta Service

Microservicio para la gestion de restaurantes. Implementa arquitectura hexagonal, seguridad JWT, persistencia con JPA y documentacion OpenAPI.

## Requisitos

- Java 25
- Gradle
- PostgreSQL

## Configuracion

Propiedades principales en `src/main/resources/application.properties`:

- `spring.datasource.*`: conexion a la base de datos
- `server.port`: puerto del servicio (por defecto 8082)
- `plazoleta.usuarios.base-url`: base URL del microservicio de usuarios
- `jwt.secret`: secreto para validar JWT

## Ejecutar

```bash
./gradlew bootRun
```

## OpenAPI / Swagger UI

- `http://localhost:8082/swagger-ui.html`
- `http://localhost:8082/v3/api-docs`

## Endpoint: crear restaurante

`POST /restaurantes`

Header requerido:

- `Authorization: Bearer <JWT>` (debe contener autoridad `ADMIN` o `ADMINISTRADOR`)

Payload:

```json
{
  "name": "Restaurante 123",
  "nit": "123456789",
  "address": "Calle 1 #2-3",
  "phone": "+573005698325",
  "urlLogo": "http://logo",
  "ownerId": 10
}
```

Respuesta 201:

```json
{
  "id": 1,
  "name": "Restaurante 123",
  "nit": "123456789",
  "address": "Calle 1 #2-3",
  "phone": "+573005698325",
  "urlLogo": "http://logo",
  "ownerId": 10
}
```

## Reglas de validacion

- `name`: obligatorio y debe contener al menos una letra (no solo numeros)
- `nit`: obligatorio y solo numerico
- `address`: obligatorio
- `phone`: obligatorio, maximo 13 caracteres, solo numerico y puede iniciar con `+`
- `urlLogo`: obligatorio
- `ownerId`: obligatorio

## Validacion de rol de propietario

Al crear un restaurante se consulta el microservicio de usuarios:

`GET http://localhost:8081/usuarios/{id}/rol`

El rol esperado para el propietario es `PROPIETARIO` (case-insensitive). Si no coincide, se responde con `400`.

## Curl de ejemplo

```bash
curl -X POST http://localhost:8082/restaurantes \
  -H "Authorization: Bearer <JWT>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Restaurante 123",
    "nit": "123456789",
    "address": "Calle 1 #2-3",
    "phone": "+573005698325",
    "urlLogo": "http://logo",
    "ownerId": 10
  }'
```

## Pruebas

```bash
./gradlew test
```
