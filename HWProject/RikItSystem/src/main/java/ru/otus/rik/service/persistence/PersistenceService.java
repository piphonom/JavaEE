package ru.otus.rik.service.persistence;

import ru.otus.rik.domain.UserEntity;

public interface PersistenceService {
    UserEntity findUserByName(String name);
    UserEntity saveUser(UserEntity user);
    void deleteUser(UserEntity user);
}
