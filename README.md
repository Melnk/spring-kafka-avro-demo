# spring-kafka-avro-demo
Microservice for asynchronous user management based on event architecture. The service provides a REST API for user operations (creation, updating, deletion), which are converted into events and sent to Apache Kafka. Consumers process these events for further actions (logging, saving to the database, etc.).
