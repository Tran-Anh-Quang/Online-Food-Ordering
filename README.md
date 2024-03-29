Project Name
============

Overview
--------
This project is a Java application built with Spring Boot 3.2.3, integrating various Spring modules including Spring Data JPA, Lombok, JWT (JSON Web Tokens), Spring Security 6.2.xx, and MySQL. It provides RESTful API endpoints for performing CRUD operations and securing the application using JWT and Spring Security.

Prerequisites
-------------
- Java Development Kit (JDK) 17
- MySQL database server
- Maven build tool

Installation and Setup
----------------------
1. **Clone the repository:**
- git clone https://github.com/Tran-Anh-Quang/Online-Food-Ordering
- cd Online-Food-Ordering
2. **Database Configuration:**
- Create a MySQL database with the name `your_database_name`.
- Configure the database connection in `application.properties` file located in `src/main/resources`:
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```

3. **Run the Application:**
- Execute the following command to run the application:
  ```
  mvn spring-boot:run
  ```

Usage
-----
- The application provides RESTful endpoints for various operations. Once the application is running, you can access these endpoints using tools like Postman or cURL.
- JWT is used for authentication and authorization. Make sure to obtain a JWT token by authenticating with the appropriate credentials.
- Ensure to include the JWT token in the Authorization header of subsequent requests.

Configuration
-------------
- The application configuration can be found in `application.properties`. Modify this file according to your requirements, such as server port, logging configuration, etc.

Dependencies
------------
- **Spring Boot 3.2.3:** Framework for creating stand-alone, production-grade Spring-based applications.
- **Spring Data JPA:** Simplifies data access layer implementation using the JPA specification.
- **Lombok:** Library to reduce boilerplate code in Java classes.
- **JWT:** JSON Web Tokens for secure communication between parties.
- **Spring Security 6.2.xx:** Provides authentication and authorization support.
- **MySQL:** Database server for storing application data.

Contributing
------------
Contributions are welcome! Please follow the standard GitHub flow (Fork, Pull Request).

License
-------
[MIT License](LICENSE)
