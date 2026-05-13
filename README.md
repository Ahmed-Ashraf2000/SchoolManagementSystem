# School Management System

A comprehensive school management platform built with Java/Spring Boot, featuring student enrollment, course management,
profile administration, secure authentication.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Backend Setup](#backend-setup)
- [Running the Project](#running-the-project)
- [Application URLs](#application-urls)
- [API and Web Endpoints](#api-and-web-endpoints)

## Overview

School Management System is an enterprise-grade web application built with Spring Boot 3.5.3. It provides comprehensive
services for student management, course enrollment, holiday scheduling, profile management, messaging, and
administrative functionality with role-based access control.

## Features

- **Student Management** - Complete student enrollment and profile management
- **Course Management** - Course creation, enrollment tracking, and class management
- **User Authentication & Authorization** - Secure authentication with role-based access control
- **Role-Based Access Control** - Student, Admin, and other role-specific permissions
- **Profile Management** - User profile creation and management
- **Holiday Management** - School holiday scheduling and management
- **Messaging System** - Real-time messaging between users
- **Dashboard** - Comprehensive dashboard with user-specific information
- **Contact Management** - Contact form and inquiry management
- **Address Management** - Multiple address handling for users
- **Data Validation** - Custom validators for data integrity
- **Security Framework** - Spring Security with Thymeleaf integration
- **Multi-Environment Support** - Dev, UAT, and Production configurations
- **Audit Trail** - Base entity tracking for created/modified timestamps
- **Exception Handling** - Global exception handling with custom error responses
- **REST API Support** - Spring Data REST endpoints with HAL Explorer

## Project Structure

```
SchoolManagementSystem/
  src/
    main/
      java/com/spring/school/
        annotation/              # Custom annotations for validation
        aspects/                 # AOP aspects for logging and cross-cutting concerns
        config/                  # Configuration classes
          SecurityConfig.java
          WebConfig.java
        controller/              # MVC and REST controllers
          AdminController.java
          ContactController.java
          DashboardController.java
          GlobalExceptionController.java
          HolidayController.java
          HomeController.java
          MessagesController.java
          ProfileController.java
          PublicController.java
          StudentController.java
        exception/               # Custom exceptions
        model/                   # JPA entities
          Address.java
          BaseEntity.java
          Contact.java
          Course.java
          Holiday.java
          PrimeClass.java
          Profile.java
          Role.java
          User.java
        repository/              # Data access layer (Spring Data JPA)
        rest/                    # REST resource configurations
        security/                # Security components
        service/                 # Business logic layer
        validation/              # Validation logic
        SchoolManagementSystemApplication.java  # Application entry point
      resources/
        application.properties        # Default configuration
        application-dev.properties    # Development profile
        application-uat.properties    # UAT profile
        application-prod.properties   # Production profile
        logback.xml                   # Logging configuration
      templates/                # Thymeleaf HTML templates
        about.html
        access-denied.html
        classes.html
        contact.html
        course-students.html
        courses_enrolled.html
        courses_secure.html
        courses.html
        dashboard.html
        error.html
        footer.html
        header.html
        holidays.html
        index.html
        login.html
        messages.html
        profile.html
        register.html
        students.html
    test/
      java/com/spring/school/
        # Unit and integration tests
  pom.xml                         # Maven configuration
  mvnw / mvnw.cmd                 # Maven wrapper scripts
```

## Technologies Used

- **Backend**: Java 17, Spring Boot 3.5.3, Spring Framework
- **Web**: Spring Web (MVC), Thymeleaf templating engine
- **Security**: Spring Security 6, Role-based access control
- **Data Access**: Spring Data JPA, Hibernate ORM
- **Database**: PostgreSQL (default), MySQL compatible
- **Model Mapping & Utilities**: Lombok
- **Validation**: Spring Validation framework, Custom validators
- **Build Tool**: Maven 3.6+
- **Logging**: SLF4J with Logback
- **REST Support**: Spring Data REST with HAL Explorer
- **Testing**: JUnit 5, Spring Boot Test, Spring Security Test
- **Containerization**: Docker support (JAR-based deployment)

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.6+** (or use the bundled `mvnw` scripts)
- **PostgreSQL 12+** (or MySQL 8+)
- **Git** (for cloning the repository)

### Backend Setup

1. **Clone or navigate to the repository**

   ```powershell
   cd "F:\my projects\SchoolManagementSystem"
   ```

2. **Build the project**

   ```powershell
   .\mvnw.cmd clean install -DskipTests
   ```

   Or on Unix-like systems:

   ```bash
   ./mvnw clean install -DskipTests
   ```

3. **Configure application properties**

   Edit `src/main/resources/application.properties` and set:

    - **Database Configuration**:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/school_management
      spring.datasource.username=postgres
      spring.datasource.password=your_password
      spring.datasource.driver-class-name=org.postgresql.Driver
      ```

    - **JPA/Hibernate Configuration**:
      ```properties
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=false
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
      ```

    - **Application Server**:
      ```properties
      server.port=8080
      server.servlet.context-path=/
      ```

4. **Environment-Specific Configurations**

    - **Development**: `src/main/resources/application-dev.properties`
    - **UAT**: `src/main/resources/application-uat.properties`
    - **Production**: `src/main/resources/application-prod.properties`

   Set active profile in `application.properties`:
   ```properties
   spring.profiles.active=dev
   ```

### Database Setup

1. **Create database** (PostgreSQL):
   ```sql
   CREATE DATABASE school_management;
   ```

2. **Database tables** will be automatically created by Hibernate based on the entity mapping using `ddl-auto=update`

## Running the Project

### Option 1: Using Maven (Development)

```powershell
# Development profile
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# UAT profile
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=uat"

# Production profile
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

Or on Unix-like systems:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Option 2: Using Built JAR

```powershell
# Build
.\mvnw.cmd clean package

# Run (Development)
java -jar target/primeschool-aws-deployment.jar --spring.profiles.active=dev

# Run (Production)
java -jar target/primeschool-aws-deployment.jar --spring.profiles.active=prod
```

### Expected Output

Once running, the application will start on: `http://localhost:8080`

## Application URLs

### Main Pages

- **Home**: `http://localhost:8080/`
- **Login**: `http://localhost:8080/login`
- **Registration**: `http://localhost:8080/register`
- **About**: `http://localhost:8080/about`
- **Contact**: `http://localhost:8080/contact`

### Authenticated Pages

- **Dashboard**: `http://localhost:8080/dashboard` (Requires login)
- **Profile**: `http://localhost:8080/profile` (Requires login)
- **Courses**: `http://localhost:8080/courses` (Requires login)
- **Enrolled Courses**: `http://localhost:8080/courses_enrolled` (Student)
- **Messages**: `http://localhost:8080/messages` (Requires login)
- **Access Denied**: `http://localhost:8080/access-denied` (Insufficient permissions)

### Admin Pages

- **Students**: `http://localhost:8080/admin/students` (Admin only)
- **Classes**: `http://localhost:8080/admin/classes` (Admin only)
- **Holidays**: `http://localhost:8080/admin/holidays` (Admin only)

## API and Web Endpoints

### Authentication & Authorization

- `GET /login` - Display login page
- `POST /login` - Process login credentials
- `GET /register` - Display registration page
- `POST /register` - Process user registration
- `GET /logout` - Logout current user

### Dashboard & Home

- `GET /` - Homepage
- `GET /dashboard` - User dashboard
- `GET /about` - About page
- `GET /contact` - Contact page
- `POST /contact` - Submit contact form

### User Management

- `GET /profile` - View user profile
- `POST /profile` - Update user profile
- `GET /admin/` - Admin dashboard (Admin only)

### Student Management

- `GET /student/` - Student view
- `GET /admin/students` - List all students (Admin)
- `POST /admin/students` - Create new student (Admin)
- `PUT /admin/students/{id}` - Update student (Admin)
- `DELETE /admin/students/{id}` - Delete student (Admin)

### Course Management

- `GET /courses` - List all courses
- `GET /courses_enrolled` - View enrolled courses
- `POST /course/enroll` - Enroll in course
- `POST /course/unenroll` - Unenroll from course
- `GET /admin/course-students` - View students in course (Admin)

### Holiday Management

- `GET /admin/holidays` - View holidays (Admin)
- `POST /admin/holidays` - Create holiday (Admin)
- `PUT /admin/holidays/{id}` - Update holiday (Admin)
- `DELETE /admin/holidays/{id}` - Delete holiday (Admin)

### Messaging

- `GET /messages` - View messages
- `POST /messages` - Send message
- `GET /messages/inbox` - View inbox

### REST API (Spring Data REST)

- `GET /api/` - HAL Explorer (view available REST endpoints)
- REST endpoints auto-generated for all entities with BaseEntity mapping

## Building and Deployment

### Create Executable JAR

```powershell
.\mvnw.cmd clean package
```

Output: `target/primeschool-aws-deployment.jar`

### Docker Build and Run

```powershell
# Build Docker image
docker build -t school-management-system:latest .

# Run container
docker run -p 8080:8080 `
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/school_management `
  -e SPRING_DATASOURCE_USERNAME=postgres `
  -e SPRING_DATASOURCE_PASSWORD=your_password `
  -e SPRING_PROFILES_ACTIVE=prod `
  school-management-system:latest
```

### AWS Deployment

The JAR file is configured as `primeschool-aws-deployment.jar` for easy AWS deployment:

```bash
# Upload to AWS and run
java -jar primeschool-aws-deployment.jar --spring.profiles.active=prod
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

