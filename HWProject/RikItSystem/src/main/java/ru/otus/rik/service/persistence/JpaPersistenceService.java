package ru.otus.rik.service.persistence;

import ru.otus.rik.domain.*;
import ru.otus.rik.service.persistence.dao.jpa.*;

import javax.persistence.*;
import java.util.List;
import java.util.function.Function;

public class JpaPersistenceService implements PersistenceService {
    private static final String PERSISTENCE_UNIT_NAME = "RikPersistenceWithMySql";
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    @Override
    public UserEntity findUserByName(String name) {
        return runTransaction((entityManager -> new JpaUserDAO(entityManager).findByName(name)));
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return runTransaction((entityManager -> new JpaUserDAO(entityManager).findByEmail(email)));
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).findAll());
    }

    @Override
    public List<UserEntity> findUsersOrderBySalaryDesc() {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).findAllOrderBySalaryDesc());
    }

    @Override
    public UserEntity findUserWithMaxSalary() {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).findByMaxSalary());
    }

    @Override
    public List<UserEntity> findUsersByParams(SearchParams params) {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).findByParams(params));
    }

    @Override
    public double getAverageSalary() {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).getAverageSalary());
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).save(user));
    }

    @Override
    public void deleteUser(UserEntity user) {
        runTransaction(entityManager -> new JpaUserDAO(entityManager).delete(user));
    }

    @Override
    public DepartmentEntity findDepartmentByNameAndLocation(String name, String location) {
        return runTransaction(entityManager -> new JpaDepartmentDAO(entityManager).findByNameAndLocation(name, location));
    }

    @Override
    public List<DepartmentEntity> findAllDepartments() {
        return runTransaction(entityManager -> new JpaDepartmentDAO(entityManager).findAll());
    }

    @Override
    public DepartmentEntity saveDepartment(DepartmentEntity department) {
        return runTransaction(entityManager -> new JpaDepartmentDAO(entityManager).save(department));
    }

    @Override
    public DepartmentEntity deleteDepartment(DepartmentEntity department) {
        return runTransaction(entityManager -> new JpaDepartmentDAO(entityManager).delete(department));
    }

    @Override
    public PositionEntity findPositionByTitle(String title) {
        return runTransaction((entityManager -> new JpaPositionDAO(entityManager).findByTitle(title)));
    }

    @Override
    public List<PositionEntity> findAllPositions() {
        return runTransaction(entityManager -> new JpaPositionDAO(entityManager).findAll());
    }

    @Override
    public PositionEntity savePosition(PositionEntity position) {
        return runTransaction((entityManager -> new JpaPositionDAO(entityManager).save(position)));
    }

    @Override
    public PositionEntity deletePosition(PositionEntity position) {
        return runTransaction((entityManager -> new JpaPositionDAO(entityManager).delete(position)));
    }

    @Override
    public RoleEntity findRoleByName(String name) {
        return runTransaction((entityManager -> new JpaRoleDAO(entityManager).findByName(name)));
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return runTransaction((entityManager -> new JpaRoleDAO(entityManager).save(role)));
    }

    @Override
    public List<RoleEntity> findAllRoles() {
        return runTransaction(entityManager -> new JpaRoleDAO(entityManager).findAll());
    }

    @Override
    public RoleEntity deleteRole(RoleEntity role) {
        return runTransaction(entityManager -> new JpaRoleDAO(entityManager).delete(role));
    }

    @Override
    public StatisticsEntity findStatisticsById(int id) {
        return runTransaction(entityManager -> new JpaStatisticsDAO(entityManager).findById(id));
    }

    @Override
    public List<StatisticsEntity> findAllStatistics() {
        return runTransaction(entityManager -> new JpaStatisticsDAO(entityManager).findAll());
    }

    @Override
    public StatisticsEntity saveStatistics(StatisticsEntity statistics) {
        return runTransaction(entityManager -> new JpaStatisticsDAO(entityManager).save(statistics));
    }

    @Override
    public StatisticsEntity deleteStatistics(StatisticsEntity statistics) {
        return runTransaction(entityManager -> new JpaStatisticsDAO(entityManager).delete(statistics));
    }

    @Override
    public void dropAll() {
        List<UserEntity> users = this.findAllUsers();
        users.forEach(this::deleteUser);

        List<DepartmentEntity> departments = this.findAllDepartments();
        departments.forEach(this::deleteDepartment);

        List<PositionEntity> positions = this.findAllPositions();
        positions.forEach(this::deletePosition);

        List<RoleEntity> roles = this.findAllRoles();
        roles.forEach(this::deleteRole);
    }

    private <T> T runTransaction(Function<EntityManager, T> function) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            manager.setFlushMode(FlushModeType.COMMIT);
            manager.getTransaction().begin();
            T result = function.apply(manager);
            manager.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            return null;
        } finally {
            manager.close();
        }
    }
}
