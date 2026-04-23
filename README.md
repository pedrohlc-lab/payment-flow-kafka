Asynchronous Payment System with Spring Boot and Kafka

This project implements a distributed financial transaction system using an event-driven architecture. It manages bank account operations (deposits and withdrawals) by decoupling the request entry point from the database processing layer using Apache Kafka.
Architecture Overview

The system follows a Producer-Consumer pattern:

    Producer: A REST Controller receives transaction requests and publishes an AccountOperationDTO to a Kafka topic.
    Message Broker: Apache Kafka handles the message persistence and delivery.
    Consumer: A dedicated listener processes the messages, applies business logic (including balance validation), and persists the final state in a PostgreSQL database.

Tech Stack

    -Java 21 (using Records for DTOs)
    -Spring Boot 4.0.5
    -Apache Kafka
    -PostgreSQL
    -Docker & Docker Compose
    -Lombok

Infrastructure Setup:

To start the required services (Kafka & PostgreSQL), run the following command in the project root:
Bash
sudo docker-compose up -d

REST API Endpoints

1. Deposit
URL: /api/payments/deposit
Method: POST
Bash
curl -X POST http://localhost:8080/api/payments/deposit \
     -H "Content-Type: application/json" \
     -d '{"id": 1, "amount": 500.0}'

2. Withdraw
URL: /api/payments/withdraw
Method: POST
Bash
curl -X POST http://localhost:8080/api/payments/withdraw \
     -H "Content-Type: application/json" \
     -d '{"id": 1, "amount": 200.0}'
     

Business Rules
    -Balance Validation: The system strictly enforces balance checks. If a withdrawal exceeds the available funds, an IllegalArgumentException is thrown, and the transaction is aborted before database persistence.
