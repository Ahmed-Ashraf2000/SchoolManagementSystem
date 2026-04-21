# School Management System

This is a comprehensive web-based application for managing school operations, built with Java and the Spring Boot framework. The system provides functionalities for handling student information, courses, holidays, and contact messages. It features a role-based security system to differentiate access between administrators and students.

## Key Features

*   **User Authentication and Authorization:** Secure login and role-based access control (e.g., ADMIN, STUDENT) powered by Spring Security.
*   **Dashboard:** A central hub for users to view relevant information after logging in.
*   **Course Management:** Allows administrators to create, update, and delete courses. Students can view and enroll in courses.
*   **Student Management:** Administrators can view and manage student information.
*   **Holiday Management:** A module for managing and displaying school holidays.
*   **Contact Message System:** Enables users to send messages to the school administration. The system tracks the status of these messages (e.g., Open, Closed).
*   **Profile Management:** Users can view and update their profiles.
*   **REST APIs:** Exposes RESTful web services for interacting with the application's data, secured with Spring Security.

## Tech Stack

*   **Backend:**
    *   Java 17
    *   Spring Boot 3.5.3
    *   Spring MVC
    *   Spring Data JPA
    *   Spring Security
    *   Spring AOP
*   **Frontend:**
    *   Thymeleaf
    *   HTML, CSS, JavaScript
*   **Database:**
    *   PostgreSQL
*   **Build Tool:**
    *   Maven
*   **Utilities:**
    *   Lombok

## Getting Started

### Prerequisites

*   Java 17 or later
*   Maven 3.2+
*   PostgreSQL

### Installation and Setup

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/Ahmed-Ashraf2000/SchoolManagementSystem.git
    cd SchoolManagementSystem
    ```

2.  **Configure the database:**
    *   Open the `src/main/resources/application.properties` file.
    *   Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your PostgreSQL database configuration.

3.  **Build and run the application:**
    ```sh
    mvn spring-boot:run
    ```
    The application will be accessible at `http://localhost:8080`.

## Project Structure

The project follows a standard Maven project structure. Here's an overview of the key packages:

*   `src/main/java/com/spring/school`: The root package for the application's source code.
    *   `config`: Contains Spring configuration classes, including security configuration.
    *   `controller`: Spring MVC controllers for handling web page requests.
    *   `model`: JPA entities that represent the database tables.
    *   `repository`: Spring Data JPA repositories for database operations.
    *   `service`: Business logic and service layer.
    *   `security`: Classes related to Spring Security implementation.
    *   `aspects`: AOP aspects for cross-cutting concerns like logging.
    *   `rest`: REST controllers for the application's API.
*   `src/main/resources`: Contains static resources, templates, and configuration files.
    *   `static`: CSS, JavaScript, and image files.
    *   `templates`: Thymeleaf templates for the web pages.
    *   `application.properties`: Application configuration file.

## REST API

The application exposes several REST endpoints for interacting with the data. These endpoints are secured and require authentication. You can explore the available endpoints using the Spring Data REST HAL Explorer at `http://localhost:8080/api`.

Some of the available endpoints include:

*   `/api/courses`: For managing courses.
*   `/api/students`: For managing students.
*   `/api/holidays`: For managing holidays.

