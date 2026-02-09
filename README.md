## CarPooling App
![CarPooling App Diagram](docs/diagram.png)

### Prerequisites

- Java 21
- Docker & Docker Compose

### Running Locally

**Option 1 — Full dev stack with hot reload (recommended)**

```bash
docker compose up -d
```

This starts all infrastructure (PostgreSQL, MongoDB, Kafka, Keycloak, Zipkin, MailDev) and application services in dev mode. A file watcher inside each container automatically recompiles when you edit files under `services/<name>/src/`, and Spring DevTools restarts the service. For dependency changes (`pom.xml`), rebuild with `docker compose up -d --build <service>`.

**Option 2 — Infrastructure only (run services from IDE)**

```bash
docker compose -f docker-compose.infra.yml up -d
```

Then start services manually from your IDE in this order:

1. `config-server` (port 8888)
2. `discovery` (port 8761)
3. Business services (ride, booking, payment, rating, notification) — any order
4. `gateway`

**Production builds**

```bash
docker compose -f docker-compose.prod.yml up -d
```

### Infrastructure Ports

| Service          | Port        |
|------------------|-------------|
| Config Server    | 8888        |
| Eureka Discovery | 8761        |
| PostgreSQL       | 5060        |
| MongoDB          | 27017       |
| Kafka            | 9092        |
| Keycloak         | 8090        |
| Zipkin           | 9411        |
| MailDev UI/SMTP  | 1080 / 1025 |