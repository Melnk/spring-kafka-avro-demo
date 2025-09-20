package ru.javabegin.micro.demo.backend.services;

import com.example.avro.UserCreatedEvent;
import com.example.avro.UserDeletedEvent;
import com.example.avro.UserUpdatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {

    @KafkaListener(topics = "users", groupId = "user-service-group")
    public void consume(Object event) {
        if (event instanceof UserCreatedEvent created) {
            System.out.println("Received UserCreatedEvent: " + created);
        } else if (event instanceof UserUpdatedEvent updated) {
            System.out.println("Received UserUpdatedEvent: " + updated);
        } else if (event instanceof UserDeletedEvent deleted) {
            System.out.println("Deleted User: " + deleted);
        } else {
            System.out.println("Unknown event: " + event);
        }
    }
}
