# Icesi Trade - Backend

> 📦 Peer-to-peer trading platform built with Spring Boot

---

## About the Project

**Icesi Trade** is a backend REST API designed to support a peer-to-peer platform for buying and selling products between users.  
This project was developed as part of a university assignment, focusing on solid backend architecture, Spring Boot practices, security, and data persistence.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA (Hibernate)
- H2 Database (for development)
- Lombok
- Maven
- JaCoCo (code coverage)
- JUnit & Mockito (unit testing)

---

## 🚀 Key Features

- 🔐 Secure login system with authentication and role-based access
- 👤 User and role management
- 📦 Product CRUD (with image, location, status, etc.)
- 📁 Categorization of products
- 💬 Internal messaging system between users
- ⭐ Review & rating system
- ❤️ Favorites functionality
- 🛎️ Notifications when receiving messages or reviews
- 📜 History tracking for product sales
- 🧪 Unit tests for core services
- 📊 JaCoCo integrated for code coverage

---

## ⚙️ How to Run the Project

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd icesi-trade
   ```

3. Run the application (using IntelliJ or Maven):
   ```bash
   mvn spring-boot:run
   ```

4. Access the H2 database (optional):
   - Visit: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: *(leave blank)*

---

## 🔑 Default Users for Testing

| Role   | Email             | Password |
|--------|-------------------|----------|
| Admin  | admin@example.com | 1234     |
| User   | user@example.com  | 1234     |
| Seller | nico@example.com  | 1234     |

> ⚠️ Passwords are not encrypted in development environment.

---

## 📂 Scripts & Initial Data

All roles, permissions, users, categories, and sample products are preloaded using `data.sql`.

---

## 🧪 Testing

To run the tests:
```bash
mvn test
```

Generate coverage report:
```bash
mvn jacoco:report
```

HTML report will be available at:
```
target/site/jacoco/index.html
```

---

## 👨‍🏫 Authors

- NicolasQR


