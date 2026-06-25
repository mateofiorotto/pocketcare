# PocketCare

A project for managing your expenses.
It will be used to learn and practice backend development: Java, Spring Boot, Spring Security, and testing.
I will also implement CI/CD practices and deploy it to a VPS using Docker.

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

| Method | Path                | Access      | Description                        |
|--------|---------------------|-------------|------------------------------------|
| GET    | `/`                 | USER, ADMIN | List expenses                      |
| GET    | `/{id}`             | USER, ADMIN | List expense by ID                 |
| GET    | `/total`            | USER, ADMIN | Get total of expenses              |
| GET    | `/total/{category}` | USER, ADMIN | Get total of expenses per category |
| POST   | `/`                 | USER, ADMIN | Create a new expense               |
| UPDATE | `/edit/{id}`        | USER, ADMIN | Update a existent expense          |
| DELETE | `/delete/{id}`      | USER, ADMIN | Delete a existent expense          |

---

## Run This Service

### With Docker Compose (recommended)

- Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- Java 21+
- Maven

---

1. Configure Environment Variables

Copy the provided example file and fill in your values:

```bash
cp .env.example .env
```

Your `.env` should look like this:

```properties
DB_USERNAME=myuser
DB_PASSWORD=mypassword123
DB_NAME=pocketcare_db
DB_URL=jdbc:postgresql://localhost:5432/pocketcare_db
```

> Both `application.properties` and `docker-compose.yml` read from this file automatically.
 
---

2. Start the Database

```bash
docker compose up -d
```

Verify it started correctly:

```bash
docker logs springboot-postgres
```

> Or check docker desktop

---

3. Run the Application

```bash
./mvnw spring-boot:run
```

> or from IDE (Intellij preferred)

The API will be available at `http://localhost:8080/api/v1`
 
---

- Troubleshooting

**Authentication error on Postgres** — if you changed credentials in `.env` and the error persists, the Docker volume
may have been initialized with old credentials. Recreate it with:

```bash
docker compose down
docker volume rm pocketcare_postgres_data
docker compose up -d
```

---

## Environment Variables

| Variable          | Default                                          | Description                  |
|-------------------|--------------------------------------------------|------------------------------|
| `DB_URL`          | `jdbc:postgresql://localhost:5432/pocketcare_db` | PostgreSQL database          |
| `DB_NAME`         | `pocketcare_db`                                  | Database name                |
| `DB_USER`         | `user`                                           | DB username                  |
| `DB_PASSWORD`     | `password123`                                    | DB password                  |
| `JWT_SECRET`      | *(dev key)*                                      | Base64-encoded secret key    |
| `JWT_EXPIRATION`  | `86400000`                                       | Access token TTL in ms (24h) |
| `JWT_REFRESH_EXP` | `604800000`                                      | Refresh token TTL in ms (7d) |

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