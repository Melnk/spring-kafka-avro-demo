package ru.javabegin.micro.demo.backend.services;

import com.example.avro.UserCreatedEvent;
import com.example.avro.UserDeletedEvent;
import com.example.avro.UserUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public UserProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreated(UserCreatedEvent event) {
        kafkaTemplate.send("users", event.getId().toString(), event);
    }

    public void sendUserUpdated(UserUpdatedEvent event) {
        kafkaTemplate.send("users", event.getId().toString(), event);
        System.out.println("Sent UserUpdatedEvent: " + event);
    }

    public void sendUserDeleted(UserDeletedEvent event) {
        kafkaTemplate.send("users", event.getId().toString(), event);
        System.out.println("Sent UserDeletedEvent: " + event);
    }
}