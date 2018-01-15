package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.UserEntity;

import java.util.List;

public interface UserDAO {
    UserEntity findByName(String name);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
    UserEntity save(UserEntity user);
    UserEntity delete(UserEntity user);
}
