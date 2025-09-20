package ru.javabegin.micro.demo.backend.controllers;

import com.example.avro.UserCreatedEvent;
import com.example.avro.UserDeletedEvent;
import com.example.avro.UserUpdatedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping({"id"})
    public ResponseEntity<String> UserUpdatedEvent(@PathVariable String id, @RequestBody UserUpdatedEvent body) {
        UserUpdatedEvent event = UserUpdatedEvent.newBuilder()
                .setEmail(body.getEmail())
                .setUsername(body.getUsername())
                .setId(id)
                .build();

        producerService.sendUserUpdated(event);
        System.out.println("Received JSON: " + body);
        return ResponseEntity.ok("User updated event sent!");
    }

    @DeleteMapping({"id"})
    public ResponseEntity<String> UserDeletedEvent(@PathVariable String id) {
        UserDeletedEvent event = UserDeletedEvent.newBuilder()
                .setId(id)
                .build();

        producerService.sendUserDeleted(event);
        System.out.println("Deleted user: " + event);
        return ResponseEntity.ok("User deleted event sent!");

    }

}
