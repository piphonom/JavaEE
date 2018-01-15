package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.domain.RoleEntity;
import ru.otus.rik.service.persistence.dao.RoleDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaRoleDAO implements RoleDAO {
    private final EntityManager entityManager;
    private final JpaBaseDAO<RoleEntity> baseDAO;

    public JpaRoleDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.baseDAO = new JpaBaseDAO<>(entityManager, RoleEntity.class);
    }

    @Override
    public RoleEntity findByName(String name) {
        return baseDAO.findSingleResultByOneParameter("name", name);
    }

    @Override
    public List<RoleEntity> findAll() {
        return baseDAO.findAll();
    }

    @Override
    public RoleEntity save(RoleEntity role) {
        return baseDAO.save(role);
    }

    @Override
    public RoleEntity delete(RoleEntity role) {
        return baseDAO.delete(role);
    }
}
