package ru.otus.rikapi.service;

import ru.otus.rikapi.entities.UserEntity;

import javax.security.sasl.AuthenticationException;

public interface AuthenticationService {
    UserEntity authenticateByEmail(String email, String password) throws AuthenticationException;
    UserEntity getCurrentUser();
}
