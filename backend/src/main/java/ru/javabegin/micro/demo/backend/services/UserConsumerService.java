package ru.javabegin.micro.demo.backend.services;

import com.example.avro.UserCreatedEvent;
import com.example.avro.UserDeletedEvent;
import com.example.avro.UserUpdatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserConsumerService {

    private final Map<String, String> users = new ConcurrentHashMap<>();

    @KafkaListener(topics = "users", groupId = "user-service-group")
    public void consume(Object event) {
        if (event instanceof UserCreatedEvent created) {
            users.put(created.getId(), created.getName());
            System.out.println("Current users: " + users);
            System.out.println("Received UserCreatedEvent: " + created);
        } else if (event instanceof UserUpdatedEvent updated) {
            users.put(updated.getId(), updated.getUsername());
            System.out.println("Current users: " + users);
            System.out.println("Received UserUpdatedEvent: " + updated);
        } else if (event instanceof UserDeletedEvent deleted) {
            users.remove(deleted.getId());
            System.out.println("Current users: " + users);
            System.out.println("Deleted User: " + deleted);
        } else {
            System.out.println("Unknown event: " + event);
        }
    }
}
