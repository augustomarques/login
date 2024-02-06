# Java Spring Boot Login System

This project is a simple login system developed using Java with Spring Boot. It employs MySQL for data storage, ensuring secure and efficient management of user credentials and session information.

## Features

- User registration
- User login/logout
- Session management
- Password encryption
- Docker Compose for easy deployment

## Technology Stack

- **Java**: The application is written in Java, leveraging Spring Boot for rapid development and ease of application setup.
- **Spring Boot**: Framework used for creating the application, focusing on simplicity and speed.
- **MySQL**: Chosen for the database to store user data securely.
- **Docker Compose**: Utilized for simplifying the deployment process, ensuring consistency across different environments.

## Getting Started

### Clone the Repository

First, clone the project repository to your local machine using:

```bash
git clone https://github.com/yourusername/java-springboot-login-system.git
cd java-springboot-login-system
```

## Running with Docker Compose
To start the application along with the MySQL database, use Docker Compose:

```bash
docker-compose up -d
```

This command builds the application and starts the services defined in docker-compose.yml.
