# Modular Parking Management System
![Project Intention](img/DriverUsesSystem.jpg)

The Modular Parking Management System is a **backend prototype** for efficient parking space management. Built with the Java Platform Module System (JPMS), Maven and Spring, it uses Clean Architecture and strong modular boundaries for maintainability and testability.

- Modular design with strict separation of concerns
- RESTful API for bookings and parking management
- User authentication and access control

This project demonstrates modern, maintainable backend architecture for academic purposes.

---

## Table of Contents
- [Clean Architecture](#clean-architecture)
- [Modules](#modules)
- [Repository Injection](#repository-injection)
- [Flow of Requests](#flow-of-requests)
- [Spring Configuration Injection](#spring-configuration-injection)
- [The Database](#the-database)
- [License](#license)

## Clean Architecture
The prototype adheres to Clean Architecture principles, ensuring that business logic is decoupled from frameworks and external systems. This promotes testability and maintainability.

![Clean Architecture Diagram](img/clean-architecture.png)

## Modules
Each feature is encapsulated in its own modules, with clear boundaries and dependencies. This modular approach allows for independent development, testing, and deployment of features.

*Modules for the account feature; other features follow the same or similar structure.*

![Account Modules Diagram](img/arch-account-modules.png)

## Repository Injection
Due to the modular design adherent to Clean Architecture, repositories can be injected without tight coupling. This is achieved through interfaces and dependency inversion.

![Repository Injection Diagram](img/di-db.png)

## Flow of Requests

![Flow of Requests Diagram](img/request-sequence.png)

## Spring Configuration Injection
Each module provides its own Spring configuration class to manage beans and dependencies. JPMS makes the configuration class visible to the system. The Service Provider Interface (SPI) loads the configuration class at runtime, making the module's beans available to Spring and enabling module injection.

![Spring Configuration Injection Diagram](img/spi-spring.png)

## The Database
DB entities and their relationships.

![Database Diagram](img/database-entities.png)

---

## License

This project is for academic purposes only and must not be used, copied or distributed by others for any purpose.
