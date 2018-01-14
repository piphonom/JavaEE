package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.UserEntity;

public interface UserDAO {
    UserEntity findByName(String name);
    UserEntity findByEmail(String email);
    UserEntity save(UserEntity user);
    UserEntity delete(UserEntity user);
}
