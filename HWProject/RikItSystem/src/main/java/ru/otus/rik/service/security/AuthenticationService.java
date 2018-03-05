package ru.otus.rik.service.security;

import ru.otus.rik.domain.UserEntity;

import javax.security.sasl.AuthenticationException;

public interface AuthenticationService {
    UserEntity authenticateByEmail(String email, String password) throws AuthenticationException;
    UserEntity getCurrentUser();
    void setCurrentUser(UserEntity user);
}
