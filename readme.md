# Event Booking System

Simple demo project showing a scalable, asynchronous event booking system.

## What this project is

- A demonstration booking system built as small microservices.
- Four services: `inventory`, `booking`, `order`, and `apigateway`.
- Uses **Kafka** for asynchronous messaging between services.
- Uses **PostgreSQL** for persistent storage.
- Package and deploy with a Helm chart located in `event-booking-system-chart`.

Key features:

- Microservices architecture
- Asynchronous communication (Kafka)
- Scalability (Kubernetes + Helm + HPAs)
- Separation of concerns and simple service contracts

## Architecture (short)

- `inventory` — manages available items and stock
- `booking` — handles booking requests and publishes events to Kafka
- `order` — consumes booking events and creates orders
- `apigateway` — single entry point for client requests
- `kafka` — message broker used to decouple services
- `postgresql` — relational storage for services

I will add diagrams here later to show message flows and deployment topology.

## Run locally (quick)

This repository includes a `docker-compose.yaml` to run all services, Kafka, and Postgres locally for development.

```bash
# build and start everything locally
docker-compose up --build

# stop
docker-compose down
```

## Deploy to Kubernetes with Helm

Install the chart (example):

```bash
helm install my-release event-booking-system-chart

# to upgrade after changes
helm upgrade my-release event-booking-system-chart

# to uninstall
helm uninstall my-release
```

Customize behavior via `values.yaml` in the chart — for example you can enable/disable in-chart Kafka or Zookeeper, adjust replica counts, and change image tags.

## Tech stack (for resume)

- Java / Spring Boot services
- Apache Kafka
- PostgreSQL
- Docker & docker-compose
- Kubernetes + Helm

## Next steps

- Add architecture and sequence diagrams (message flows)
- Add health checks and observability notes
- Add CI/CD example for building and publishing images

If you want, I can add a simple diagram and a short `helm lint` run in the repo next.
