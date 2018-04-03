package ru.otus.rik.service.user.exceptions;

public class UserModificationException extends Exception {
    public UserModificationException() {
    }

    public UserModificationException(String message) {
        super(message);
    }
}
