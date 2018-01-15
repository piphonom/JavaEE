package ru.otus.rik.service.persistence;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.RoleEntity;
import ru.otus.rik.domain.UserEntity;

import javax.management.relation.Role;
import java.util.List;

public interface PersistenceService {
    UserEntity findUserByName(String name);
    UserEntity findUserByEmail(String email);
    List<UserEntity> findAllUsers();
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
}
