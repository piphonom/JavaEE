package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.domain.UserEntity;
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
    public UserEntity save(UserEntity user) {
        return baseDAO.save(user);
    }

    @Override
    public UserEntity delete(UserEntity user) {
        return baseDAO.delete(user);
    }
}
