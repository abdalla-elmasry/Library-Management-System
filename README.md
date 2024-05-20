# Library Management System

The Library Management System is a software application tailored to facilitate the management of books, patrons, and borrowing records within a library setting.
* [Spring Boot 3.1.1](https://spring.io/projects/spring-boot)
* [Spring Data JPA 3.1.2](https://spring.io/projects/spring-data-jpa)
* [SpringDoc 2.1.0](https://springdoc.org/)
* [Maven 3.8.1](https://maven.apache.org)

### Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.8.1](https://maven.apache.org)

### Steps to set up a PostgreSQL database:

1. Install PostgreSQL on your machine. You can download it from the official
   website [here](https://www.postgresql.org/download/).
2. Once you have installed PostgreSQL, you can create a new database by using the command prompt. First, log in to the
   PostgreSQL database server using pgAdmin or the command prompt. Then, enter the command "CREATE DATABASE library-management-system;"
   to create a new database¹.
3. You can also create a database using pgAdmin, a GUI-based tool. To create a database using pgAdmin, right-click on
   the server name and select Create > Database...¹.
4. After creating the database, you can connect to it using JDBC driver in your Spring Boot application. Here is an
   example of how to configure your application.yml file:

### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `backend/developer/librarymanagementsystem` class from your IDE.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

#### MacOS/Linux:

```shell
./mvnw spring-boot:run
```

#### Windows:

```shell
mvn spring-boot:run
```

### APIs documentation

* [Local Environment](http://localhost:8080/swagger-ui/index.html)