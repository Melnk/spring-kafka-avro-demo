package ru.javabegin.micro.demo.backend.services;

import com.example.avro.UserCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {

    @KafkaListener(topics = "users", groupId = "user-service-group")
    public void consume(UserCreatedEvent event) {
        System.out.println("Received: " + event);
    }
}
