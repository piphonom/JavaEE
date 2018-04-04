package ru.otus.rik.service.user;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.user.exceptions.UserCreationException;
import ru.otus.rik.service.user.exceptions.UserModificationException;

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
