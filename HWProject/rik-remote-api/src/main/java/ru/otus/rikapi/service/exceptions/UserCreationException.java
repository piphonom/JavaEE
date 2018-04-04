package ru.otus.rikapi.service.exceptions;

public class UserCreationException extends Exception {
    public UserCreationException() {
    }

    public UserCreationException(String message) {
        super(message);
    }
}
