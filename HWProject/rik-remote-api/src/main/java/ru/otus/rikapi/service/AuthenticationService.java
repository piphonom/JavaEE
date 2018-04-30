package ru.otus.rikapi.service;

import ru.otus.rikapi.entities.RoleEntity;
import ru.otus.rikapi.entities.UserEntity;

import javax.security.sasl.AuthenticationException;
import java.util.Set;

public interface AuthenticationService {
    UserEntity authenticateByEmail(String email, String password) throws AuthenticationException;
    Set<String> getUserRoles(String email);
}
