# QPay
### Payment system by generating QR codes.

## Description
The project is aimed at implementing a payment system with QR code generation in a microservice architecture.

## Goals
- [x] Apply a monorepo approach to the interaction between services.
- [x] Design microservices to achieve high reliability and scalability of the system.
- [x] Use docker containers to run external components independently.
- [x] Creating migration scripts for database version control.
- [x] Configure the CI-pipeline for GitHub to automatically build the project, run tests.
- [x] Use Apache Kafka to store transaction data in order to increase fault tolerance.
- [x] Create unit and integration tests with code coverage over 80%.
- [ ] Implementation of authentication and authorization using JWT.
- [ ] Implementation of API-Gateway and Service Discovery to simplify communication between services and clients.
- [ ] Create fronted app

## Structure
Project contains the following services.

Architecture: https://drive.google.com/file/d/1Jn5VrUUoQQtLY0N9Jk_7VIYwRXwG2yOq/view?usp=sharing
- **User manager** - handles user information.
- **Payment manager** - performs payment process.
- **Notification manager** - send emails to the users.
- **Transaction manager** - stores information about payment transactions.
- **QR generator** - generates a QR-code based on transaction information.
- **Auth manager** -  performs authentication and authorization.
- **Service Discovery** - allows services to find and communicate with each other.
- **API Gateway** - monitors and routes requests from clients to services.

## How to run
- Run docker-compose.yaml in package ***.docker***.
- Run each service (check each service for more information).
- Profit.

## Technologies

- [Server] SpringBoot, Gradle

- [Frontend] React, Typescript

- [Databases] Postgres, MongoDB

- [Tools] Docker, Kafka, API-Gateway, Circuit-breaker, Service Discovery