package ru.otus.rikapi.service.exceptions;

public class UserModificationException extends Exception {
    public UserModificationException() {
    }

    public UserModificationException(String message) {
        super(message);
    }
}
