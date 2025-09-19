package ru.javabegin.micro.demo.backend.dto;

public class CreateUserRequest {
    private String name;
    private String email;

    // геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
