package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.UserEntity;

import java.util.List;

public interface UserDAO {
    UserEntity findByName(String name);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
    List<UserEntity> findAllOrderBySalaryDesc();
    UserEntity findByMaxSalary();
    UserEntity save(UserEntity user);
    UserEntity delete(UserEntity user);
}
