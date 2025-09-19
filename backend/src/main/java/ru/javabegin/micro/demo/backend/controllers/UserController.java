package ru.javabegin.micro.demo.backend.controllers;

import com.example.avro.UserCreatedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.micro.demo.backend.dto.CreateUserRequest;
import ru.javabegin.micro.demo.backend.services.UserProducerService;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserProducerService producerService;

    public UserController(UserProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreatedEvent body) {
        UserCreatedEvent event = UserCreatedEvent.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setName(body.getName())
                .setEmail(body.getEmail())
                .setCreatedAt(System.currentTimeMillis())
                .build();

        producerService.sendUserCreated(event);
        System.out.println("Received JSON: " + body);
        return ResponseEntity.ok("User created event sent!");
    }

}
