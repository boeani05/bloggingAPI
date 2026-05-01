# Blogging Platform API

A RESTful backend for a blogging platform built with Spring Boot, Spring Web MVC, and Spring Data JPA.

This project is implemented as part of the roadmap.sh project requirement:
**https://roadmap.sh/projects/blogging-platform-api**

## Features

- Create blog posts
- Read all posts
- Read a post by ID
- Search posts by term (`title`, `content`, `category`)
- Update existing posts
- Delete posts
- Validation and centralized error handling

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Validation (Jakarta Validation)
- H2 (runtime)
- PostgreSQL dependency available
- JUnit 5 + Mockito + MockMvc
- Maven Wrapper

## Project Structure

```text
src/main/java/com/boeani/bloggingAPI
|- controller
|  |- GetController.java
|  |- PostController.java
|  |- PutController.java
|  \- DeleteController.java
|- dto/request
|  \- CreatePostRequest.java
|- entity
|  \- Post.java
|- exceptions
|  \- GlobalExceptionHandler.java
|- repository
|  \- PostRepository.java
|- service
|  \- PostService.java
\- BloggingApiApplication.java

src/test/java/com/boeani/bloggingAPI
|- controller
|- service
\- BloggingApiApplicationTests.java
```

## API Endpoints

### Posts

- `GET /posts` - Get all posts
- `GET /posts/{id}` - Get post by ID
- `GET /posts?term={term}` - Search posts by term
- `POST /posts` - Create a post
- `PUT /posts/{id}` - Update a post
- `DELETE /posts/{id}` - Delete a post

## Request Body (Create/Update)

```json
{
  "title": "My first post",
  "content": "Hello from my API",
  "category": "Tech",
  "tags": ["spring", "java"]
}
```

## Getting Started

### Prerequisites

- Java 21+
- Maven (optional, wrapper included)

### 1) Clone the repository

```bash
git clone https://github.com/boeani05/bloggingAPI.git
cd bloggingAPI
```

### 2) Configure database

`src/main/resources/application.properties` is gitignored in this project.
Create it locally with your own values.

Example (H2 file database):

```properties
spring.datasource.url=jdbc:h2:file:./data/blogdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Example (PostgreSQL):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blogging
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 3) Run the application

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Running Tests

Run all tests:

```bash
./mvnw test
```

Windows PowerShell:

```powershell
.\mvnw.cmd test
```

## Error Handling

The API uses a centralized `GlobalExceptionHandler` and returns meaningful HTTP codes, for example:

- `400 Bad Request` for validation/business input errors
- `404 Not Found` when a post does not exist

## Notes

- The project currently uses separate controllers by HTTP concern (`Get`, `Post`, `Put`, `Delete`).
- Search is case-insensitive and implemented via a distinct repository query across multiple fields.

## License

This project is for learning and portfolio purposes.
