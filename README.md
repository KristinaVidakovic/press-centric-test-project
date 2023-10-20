# Project Name

Press Centric Test Project

## Project Description

This project was developed as part of the selection process in the company Press Centric, based on the previously provided instructions and guidelines.

### Key Features

- **CRUD Operations**: The project showcases the fundamental CRUD operations for managing user data. It serves as an educational resource to help developers understand how to create, read, update, and delete user records.

## CRUD Operations for USER Entity

This project includes a set of CRUD (Create, Read, Update, Delete) operations for the "USER" entity. These operations allow you to interact with user data in the system. Below are the available methods:

### GET /users

- **Description**: Returns a list of all users stored in the database.
- **Endpoint**: `/users`
- **HTTP Method**: GET
- **Response**: JSON representation of all users.

### GET /users/id

- **Description**: Retrieves a user by their unique ID.
- **Endpoint**: `/users/id`
- **HTTP Method**: GET
- **Request Parameter**: `id` (User ID)
- **Response**: JSON representation of the user with the specified ID.

### POST /users

- **Description**: Inserts a new user into the database.
- **Endpoint**: `/users`
- **HTTP Method**: POST
- **Request Body**: JSON representation of the user to be created.
- **Response**: NO CONTENT

### PUT /users/id

- **Description**: Updates an existing user based on their unique ID.
- **Endpoint**: `/users/id`
- **HTTP Method**: PUT
- **Request Parameter**: `id` (User ID)
- **Request Body**: JSON representation of the user's updated information.
- **Response**: NO CONTENT

### DELETE /users/id

- **Description**: Deletes a user by their unique ID.
- **Endpoint**: `/users/id`
- **HTTP Method**: DELETE
- **Request Parameter**: `id` (User ID)
- **Response**: NO CONTENT

## Getting Started

The `application.properties` contains information necessary for connecting to the database. After configuring the database, you can easily start the project.

## Usage

Examples of requests and responses are demonstrated within the Postman collection located within the project.

## Technologies Used

- **Java** - `Spring Boot`
- **PostgreSQL**