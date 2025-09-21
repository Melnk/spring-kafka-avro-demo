# spring-kafka-avro-demo
Microservice for asynchronous user management based on event architecture. The service provides a REST API for user operations (creation, updating, deletion), which are converted into events and sent to Apache Kafka. Consumers process these events for further actions (logging, saving to the database, etc.).

## Key Features:
· Event-driven architecture using Apache Kafka

· Data serialization via Avro schemas

· REST API for user management

· Asynchronous processing of operations

· Willingness to scale and add new customers

## Technological stack:
- Java 17, Spring Boot
- Apache Kafka, Schema Registry
- Avro (data serialization)
- Docker, Docker Compose
- Swagger / OpenAPI
- Spring Actuator


The project demonstrates work with modern patterns of distributed systems and event-driven architecture.
