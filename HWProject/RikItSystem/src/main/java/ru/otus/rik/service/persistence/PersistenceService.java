package ru.otus.rik.service.persistence;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.RoleEntity;
import ru.otus.rik.domain.UserEntity;

import java.util.List;

public interface PersistenceService {
    UserEntity findUserByName(String name);
    UserEntity findUserByEmail(String email);
    List<UserEntity> findAllUsers();
    List<UserEntity> findUsersOrderBySalaryDesc();
    UserEntity findUserWithMaxSalary();
    List<UserEntity> findUsersByParams(SearchParams params);
    double getAverageSalary();
    UserEntity saveUser(UserEntity user);
    void deleteUser(UserEntity user);

    DepartmentEntity findDepartmentByNameAndLocation(String name, String location);
    List<DepartmentEntity> findAllDepartments();
    DepartmentEntity saveDepartment(DepartmentEntity department);
    DepartmentEntity deleteDepartment(DepartmentEntity department);

    PositionEntity findPositionByTitle(String title);
    List<PositionEntity> findAllPositions();
    PositionEntity savePosition(PositionEntity position);
    PositionEntity deletePosition(PositionEntity position);

    RoleEntity findRoleByName(String name);
    List<RoleEntity> findAllRoles();
    RoleEntity saveRole(RoleEntity role);
    RoleEntity deleteRole(RoleEntity role);

    void dropAll();
}
