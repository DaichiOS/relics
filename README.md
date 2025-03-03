# Relics Backend 📚

This repo hosts the Relics Backend system for a book reading platform. It contains java APIs built with Spring Boot, allowing you to create users and books via a flexible MongoDB set up.

## What's Inside? 🧐

### Core Features

- Book management (add, update, delete, search)
- User system with secure password handling
- MongoDB for flexible data storage
- RESTful API endpoints

### Main Components

- **Book Service**: Handles all the book-related operations
- **User Service**: Manages user accounts and authentication
- **MongoDB Integration**: Stores all our data in a flexible NoSQL format
- **Security Layer**: Keeps everything nice and secure

## Tech Stack 🛠

- Java 21
- Spring Boot 3.2.0
- MongoDB
- Spring Security
- Gradle

## API Endpoints 🌐

### Books

- `GET /books` - Get all books
- `GET /books/{id}` - Get a specific book
- `POST /books` - Create a new book
- `PUT /books/{id}` - Update a book
- `DELETE /books/{id}` - Delete a book
- `GET /books/search` - Search books by name, author, or category

### Users

- `POST /users/register` - Register a new user
- `GET /users` - Get all users
- `PUT /users/{id}` - Update user details
- `DELETE /users/{id}` - Delete a user

## Getting Started 🚀

1. Make sure you have MongoDB installed and running
2. Clone this repository
3. Run `./gradlew bootRun`
4. The server will start on `http://localhost:8080`

### Quick Test

Want to populate some test data? Just send a POST request to:

```
POST http://localhost:8080/createTestData
```

## What's Next? 🎯

- Frontend in Next.js
- More APIs

Feel free to contribute or reach out with questions! 😊
