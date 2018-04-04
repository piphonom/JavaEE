package ru.otus.rik.service.user.exceptions;

public class UserCreationException extends Exception {
    public UserCreationException() {
    }

    public UserCreationException(String message) {
        super(message);
    }
}
