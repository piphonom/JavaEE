package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.SearchParams;
import ru.otus.rik.service.persistence.dao.UserDAO;

import javax.persistence.*;
import java.util.List;

public class JpaUserDAO implements UserDAO {
    private EntityManager entityManager;
    private final JpaBaseDAO<UserEntity> baseDAO;

    public JpaUserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.baseDAO = new JpaBaseDAO<>(entityManager, UserEntity.class);
    }

    @Override
    public UserEntity findByName(String name) {
        return baseDAO.findSingleResultByOneParameter("name", name);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return baseDAO.findSingleResultByOneParameter("email", email);
    }

    @Override
    public List<UserEntity> findAll() {
        return baseDAO.findAll();
    }

    @Override
    public List<UserEntity> findAllOrderBySalaryDesc() {
        Query query = entityManager.createQuery("from UserEntity u order by u.positionRef.salary desc");
        return query.getResultList();
    }

    @Override
    public UserEntity findByMaxSalary() {
        Query query = entityManager.createNamedStoredProcedureQuery("findByMaxSalary")
                .setMaxResults(1);
        return (UserEntity) query.getSingleResult();
    }

    @Override
    public List<UserEntity> findByParams(SearchParams params) {
        Query query = entityManager.createQuery("from UserEntity u where u.name like :name " +
                                                                          "and u.departmentRef.name like :department " +
                                                                          "and u.departmentRef.location like :location")
                .setParameter("name", params.getName())
                .setParameter("department", params.getDepartment())
                .setParameter("location", params.getLocation());
        return query.getResultList();
    }

    @Override
    public double getAverageSalary() {
        Query query = entityManager.createQuery("select avg(u.positionRef.salary) from UserEntity u")
                .setMaxResults(1);
        return (Double) query.getSingleResult();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return baseDAO.save(user);
    }

    @Override
    public UserEntity delete(UserEntity user) {
        return baseDAO.delete(user);
    }
}
