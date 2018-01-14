package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.dao.UserDAO;

import javax.persistence.*;

public class JpaUserDAO implements UserDAO {
    //private EntityManager entityManager;
    private final JpaBaseDAO<UserEntity> baseDAO;

    public JpaUserDAO(EntityManager entityManager) {
        baseDAO = new JpaBaseDAO<>(entityManager, UserEntity.class);
    }

    @Override
    public UserEntity findByName(String name) {
        return baseDAO.findByOneStringParameter("name", name);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return baseDAO.findByOneStringParameter("email", email);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return baseDAO.save(user);
    }

    @Override
    public UserEntity delete(UserEntity user) {
        baseDAO.delete(user);
        return user;
    }
}
