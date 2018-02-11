package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.SearchParams;

import java.util.List;

public interface UserDAO {
    UserEntity findByName(String name);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
    List<UserEntity> findAllOrderBySalaryDesc();
    UserEntity findByMaxSalary();
    List<UserEntity> findByParams(SearchParams params);
    double getAverageSalary();
    UserEntity save(UserEntity user);
    UserEntity delete(UserEntity user);
}
