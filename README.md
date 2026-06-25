# Flinders Coffee — User Service

Part of the [Flinders Coffee E-Commerce](https://github.com/mateofiorotto/ecommerce-docker-compose) platform, a
microservices-based e-commerce project for coffee-related products (beans, makers, grinders, and more).

This service is responsible for **authentication, authorization, and user profile management**.

---

## Tech Stack

| Layer         | Technology                            |
|---------------|---------------------------------------|
| Language      | ☕ Java 21                             |
| Framework     | 🌱 Spring Boot 4.1.0                  |
| Security      | 🔒 Spring Security + JWT (role-based) |
| Persistence   | 📁 Spring Data JPA + PostgreSQL       |
| Validation    | ✅ Spring Validation                   |
| Documentation | 📃 SpringDoc OpenAPI 3 (Swagger)      |
| Container     | 📦 Docker + Docker Compose            |
| Testing       | 🧪 JUnit 5 + Mockito                  |
| Frontend      | 🖥️ Thymeleaf                         |

 
---

## Endpoints

### Auth — `/api/v1/auth`

| Method | Path        | Access | Description                  |
|--------|-------------|--------|------------------------------|
| POST   | `/register` | Public | Register a new user          |
| POST   | `/login`    | Public | Login and receive JWT tokens |
| POST   | `/refresh`  | Public | Refresh access token         |

### Expense — `/api/v1/expenses`

| Method | Path           | Access      | Description           |
|--------|----------------|-------------|-----------------------|
| GET    | `/`            | USER, ADMIN | Get own profile       |
| GET    | `/{id}`        | USER, ADMIN | Get own profile       |
| GET    | `/total`       | USER, ADMIN | Get total of expenses |
| POST   | `/`            | USER, ADMIN | List all users        |
| UPDATE | `/edit/{id}`   | USER, ADMIN | Edit own profile      |
| DELETE | `/delete/{id}` | USER, ADMIN | Get user by ID        |

---

## Run This Service

### With Docker Compose (recommended)
... To-Do

---

## Environment Variables

| Variable          | Default     | Description                  |
|-------------------|-------------|------------------------------|
| `DB_HOST`         | `localhost` | PostgreSQL host              |
| `DB_PORT`         | `5432`      | PostgreSQL port              |
| `DB_NAME`         | `user_db`   | Database name                |
| `DB_USER`         | `postgres`  | DB username                  |
| `DB_PASSWORD`     | `postgres`  | DB password                  |
| `JWT_SECRET`      | *(dev key)* | Base64-encoded secret key    |
| `JWT_EXPIRATION`  | `86400000`  | Access token TTL in ms (24h) |
| `JWT_REFRESH_EXP` | `604800000` | Refresh token TTL in ms (7d) |

> ⚠️ Never commit real secrets. Use a `.env` file locally (already in `.gitignore`).

---

## Project Structure

For now, this is a basic-structure. Will be updated.

```
src/main/java/com/flinders/user_service/
├── controller/
│   ├── AuthController.java         # /api/v1/auth/**
├── service/
│   ├── AuthService.java
│   ├── UserService.java
│   └── impl/
│       ├── AuthServiceImpl.java
│       └── UserServiceImpl.java
├── security/
│   ├── filter/
│   │   └── JwtAuthenticationFilter.java
│   └── service/
│       ├── JwtService.java
│       └── UserDetailsServiceImpl.java
├── entity/
│   ├── User.java
│   └── Role.java 
├── repository/
│   └── UserRepository.java
├── dto/
│   ├── request/
│   │   ├── RegisterRequest.java
│   │   └── LoginRequest.java
│   └── response/
│       ├── AuthResponse.java
│       └── UserResponse.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── EmailAlreadyExistsException.java
│   └── UsernameAlreadyExistsException.java
└── config/
    ├── SecurityConfig.java        
    └── JpaConfig.java               
```