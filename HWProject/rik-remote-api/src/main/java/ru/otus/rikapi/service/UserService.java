package ru.otus.rikapi.service;

import ru.otus.rikapi.entities.DepartmentEntity;
import ru.otus.rikapi.entities.PositionEntity;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.service.exceptions.UserCreationException;
import ru.otus.rikapi.service.exceptions.UserModificationException;

import java.util.List;

public interface UserService {
    UserEntity findUserByEmail(String email);
    UserEntity createUser(String email, String location, String department, String position) throws UserCreationException;
    UserEntity editUser(String email, String location, String department, String position) throws UserModificationException;
    void deleteUser(String email) throws UserModificationException;
    List<UserEntity> findAllUsers();
    List<DepartmentEntity> findAllDepartments();
    List<PositionEntity> findAllPositions();
    double getAverageSalary();
}
