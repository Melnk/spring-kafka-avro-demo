package ru.javabegin.micro.demo.backend.services;

import com.example.avro.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public UserProducerService(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreated(UserCreatedEvent event) {
        kafkaTemplate.send("users", event.getId().toString(), event);
    }
}
