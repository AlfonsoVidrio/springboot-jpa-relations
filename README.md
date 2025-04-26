# Spring Boot JPA Relationship Practice

## Description

This is a console application built with **Spring Boot** and **JPA** to explore and understand different types of relationships between entities in a relational database.  
It includes practical examples of:

- **One-to-One**
- **One-to-Many**
- **Many-to-Many**

The project allows you to perform basic **CRUD operations** and observe how entity associations are handled using JPA annotations, cascading operations, and join tables. It's designed as a learning tool for understanding entity mapping and data consistency in a Spring Boot application.

## Setup

To get started with this project, configure the following environment variables for your database connection and Spring Boot settings:

### Required Environment Variables

```properties
spring.application.name=springboot-jpa-relationship
spring.output.ansi.enabled=always

spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

### Variables to Configure

- **DB_HOST**: The host address of your MySQL database (e.g., `localhost`).
- **DB_PORT**: The port on which your MySQL server is running (default is `3306`).
- **DB_NAME**: The name of your database.
- **DB_USERNAME**: The username to connect to the MySQL database.
- **DB_PASSWORD**: The password to connect to the MySQL database.

### Database Setup

Before running the application, make sure the database exists.  
Spring Boot with Hibernate can automatically create the tables based on your entity classes, but the **database itself must be created manually** if it does not already exist.

To create the database:

1. Log into your MySQL server:
   - You can use a MySQL client or any tool like MySQL Workbench or phpMyAdmin.

2. Run the following SQL command:

   ```sql
   CREATE DATABASE your_database_name;
   ```

Replace `your_database_name` with the value you've set for `DB_NAME` in the environment variables.

Once the environment variables are configured and the database is created, the application will be able to run successfully, automatically generating the required tables and allowing you to test different entity relationships.

> ℹ️ With `spring.jpa.hibernate.ddl-auto=create`, the schema will be **recreated on every application start**, which is useful for development and testing but not recommended for production environments.
