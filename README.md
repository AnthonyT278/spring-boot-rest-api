# Store REST API

Spring Boot 3 / Java 17 API with PostgreSQL, JWT authentication, products, orders, Docker, and OpenAPI.

## Run with Docker

```bash
docker compose up --build
```

API: `http://localhost:8080`  
Swagger UI: `http://localhost:8080/swagger-ui.html`  
OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Authentication

Register or log in:

```http
POST /api/auth/register
{"email":"user@example.com","password":"password123"}
```

```http
POST /api/auth/login
{"email":"user@example.com","password":"password123"}
```

Send the returned token as `Authorization: Bearer <token>`.

## Endpoints

- `GET|POST|PUT|DELETE /api/products` (authenticated)
- `GET|POST /api/orders` (authenticated; users can only view their own orders)
- `GET /api/orders/{id}`

Product creation example:

```json
{"name":"Keyboard","description":"Mechanical keyboard","price":79.99,"stock":20}
```

Order creation example:

```json
{"items":[{"productId":1,"quantity":2}]}
```

Set a strong `JWT_SECRET` in production. The current JPA setting is `ddl-auto=update`; use migrations such as Flyway for production schema management.
