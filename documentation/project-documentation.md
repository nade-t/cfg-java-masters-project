# INTRODUCTION

## Goals:
- Create a backend system for managing bookstore operations
- Implement endpoints for:
    - book management
    - purchase processing
    - refunds

# BACKGROUND

- CFG Masters+ Java group project
- Showcase knowledge in Java, Spring Boot, Lombok, TDD and deployments

# SPECIFICATIONS AND DESIGN

## Requirements:

### Technical:
- RESTful API endpoints to:
    - `POST/ add_book`
    - `GET/ books`
    - `POST/ purchase_book`
    - `POST/ return_book (refund)`
- Relational database to persist book and transaction data

### Nontechnical:
- User-friendly API documentation using Swagger/OpenAPI UI.
- Maintainability and readability of code.

## Design and Architecture:
- Architecture diagram (Controller → Service → Repository)
- Design pattern: Model-View-Controller (MVC)
- How endpoints interact with database and other components.

## Tools and Libraries:
- Spring Boot modules: Web, Data JPA
- JUnit
- Lombok
- MySQL Workbench
- Postman/Insomnia for API testing.
- Git/GitHub for version control and collaboration

# TESTING AND EVALUATION

## Unit Testing
- Use JUnit and Mockito to test services and controllers in isolation
- Mock external dependencies (e.g., repositories)
- SpringBootTest? MockMvc?

## Functional Testing
- End-to-end user flow simulations:
    - Adding a book and verifying it appears in inventory
    - Simulating a full purchase and refund cycle

## Edge case handling:
- Attempt to purchase out-of-stock books
- Attempt refunds for invalid transactions  

## Manual Test Plan

| Test Case | Endpoint                             | Description                              | Input Example                                                                 | Expected Outcome                                         |
|-----------|--------------------------------------|------------------------------------------|-------------------------------------------------------------------------------|----------------------------------------------------------|
| 1. Add a Book | `POST /books`                        | Add a new book to inventory             | `{ "title": "Test Book", "author": "Jane Doe", "price": 9.99, "copiesAvailable": 5 }` | 201 Created; book appears in database with correct stock |
| 2. Get All Books | `GET /books`                         | Retrieve list of all available books    | _None_                                                                        | 200 OK: JSON array including the newly added book        |
| 3. Purchase a Book | `POST /books/purchase(ADD ENDPOINT)) | Simulate a book purchase (stock decrease) | Query params: `bookId=1`, `quantity=2`                                       | 200 OK: `copies_available` reduced number                |

