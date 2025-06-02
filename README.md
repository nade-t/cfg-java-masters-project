# CFG Java Masters Project
Group project for the CFG Java Masters

## Project Description

A backend application built with Java and Spring Boot that uses REST APIs and a MySQL database. 
This project manages a bookstore's inventory and transactions, including purchases.

------

## Features

- View all books in the Book Store
- Add a new book to the store
- Delete a book from the store
- Update a book's record

------

## Software Requirements
To run this project you will need:

- Java 21 or higher, Spring Boot
- IDE (IntelliJ IDEA)
- MySQL database integration with JPA
- RESTful API with OpenAPI documentation
- Unit testing using JUnit and Mockito

------

## Getting Started

### Installation

```bash
git clone https://github.com/nade-t/cfg-java-masters-project.git
```

```bash
cd cfg-java-masters-project
```

------

## Configuration

The app is configured using application.yml:

Locate the application.yml.example in the resources directory. 
Replace credentials with your own username and MySQL password


## Database Setup

Create your MySQL database and table using the .sql script included in the project:
Location: src/main/resources/ADD IN SQL DATABASE!


## Run the Application

Make sure MySQL is running
Create the database using the .sql file
Update application.yml with DB credentials
Run the application: in IntelliJ run the main class

------

## API Endpoints

| Method | Endpoint   | Description         |
|--------|------------|---------------------|
| GET    | /******    | Retrieves all books |
| POST   | /******    | Adds a new book     |
| PUT    | /******    | Updates a book      |
| Delete | /******    | Adds a new book     |


------

## Architecture Overview
- Controllers handle HTTP requests
- Services contain business logic
- Repositories handle DB interactions


```mermaid
flowchart TD
    
```


------

## Open API Documentation

For full documentation visit: ( **add link to swagger ui!** )

------

### Authors
Violeta - 
Nade -
Jessie - 
Radhika - 
