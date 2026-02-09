# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

CarPooling is a BlaBlaCar-inspired carpooling platform built with Spring Boot 3 microservices and Spring Cloud. Monorepo with independent services (no parent POM aggregator).

## Tech Stack

- Java 21, Spring Boot 3.3.5, Spring Cloud 2023.0.3
- Maven 3.9.9 (wrapper included per service)
- PostgreSQL (ride, booking, payment databases), MongoDB (ratings, notifications)
- Apache Kafka (KRaft mode, no Zookeeper) for async messaging
- Keycloak for authentication (OAuth2/JWT)
- Eureka for service discovery, Spring Cloud Config for centralized config
- Zipkin for distributed tracing, MailDev for email testing

## Build & Run Commands

Each service is built independently from its own directory:

```bash
# Build a single service
./services/<service-name>/mvnw -f services/<service-name>/pom.xml clean package

# Run a single service
./services/<service-name>/mvnw -f services/<service-name>/pom.xml spring-boot:run

# Run tests for a single service
./services/<service-name>/mvnw -f services/<service-name>/pom.xml test

# Run a specific test class
./services/<service-name>/mvnw -f services/<service-name>/pom.xml test -Dtest=ClassName

# Full dev stack with hot reload (infra + services)
docker compose up -d

# Infrastructure only (for running services from IDE)
docker compose -f docker-compose.infra.yml up -d

# Production builds
docker compose -f docker-compose.prod.yml up -d
```

## Startup Order

1. `docker compose up -d` — starts everything (infra + services with hot reload and correct startup ordering)
2. Or for IDE-based development: `docker compose -f docker-compose.infra.yml up -d` (infrastructure only), then start services manually:
   1. `config-server` (port 8888) — must start first, other services pull config from it
   2. `discovery` (port 8761) — Eureka, must be up before business services register
   3. Business services (ride, booking, payment, rating, notification) — any order
   4. `gateway` — API entry point, routes to services via Eureka

## Architecture

- **API Gateway** → routes `/api/v1/rides/**`, `/api/v1/bookings/**`, `/api/v1/ratings/**`, `/api/v1/payments/**` to services via Eureka load balancing. Validates JWT from Keycloak.
- **Ride Service** → drivers publish rides, passengers search. Owns `ride_db` (PostgreSQL).
- **Booking Service** → seat reservations. Calls Ride (OpenFeign) to reserve seats, calls Payment (OpenFeign) to hold funds. Produces Kafka events. Owns `booking_db` (PostgreSQL).
- **Payment Service** → holds funds on booking, releases to driver after ride completion. Produces Kafka events. Owns `payment_db` (PostgreSQL).
- **Rating Service** → mutual driver/passenger reviews after ride completion. Owns `rating_db` (MongoDB).
- **Notification Service** → consumes Kafka events from booking and payment topics, sends emails via MailDev. Owns `notification_db` (MongoDB).

## Conventions

- **Package naming**: `com.eldeeb.<servicename>` (e.g., `com.eldeeb.discovery`, `com.eldeeb.configserver`)
- **Service directory**: all services live under `services/`
- **Config files**: each service's external config goes in `services/config-server/src/main/resources/configurations/<service-name>.yml`
- **Config import**: every service's `application.yml` includes `spring.config.import: optional:configserver:http://localhost:8888`
- **No parent POM**: each service has its own independent `pom.xml` with Spring Boot 3.3.5 parent

## Infrastructure Ports

| Service | Port |
|---------|------|
| Config Server | 8888 |
| Eureka Discovery | 8761 |
| PostgreSQL | 5060 |
| MongoDB | 27017 |
| Kafka | 9092 |
| Zipkin | 9411 |
| MailDev UI / SMTP | 1080 / 1025 |
