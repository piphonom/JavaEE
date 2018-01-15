package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.service.persistence.dao.PositionDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaPositionDAO implements PositionDAO {

    private final EntityManager entityManager;
    private final JpaBaseDAO<PositionEntity> baseDAO;

    public JpaPositionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.baseDAO = new JpaBaseDAO<>(entityManager, PositionEntity.class);
    }

    @Override
    public PositionEntity findByTitle(String title) {
        return baseDAO.findSingleResultByOneParameter("title", title);
    }

    @Override
    public List<PositionEntity> findAll() {
        return baseDAO.findAll();
    }

    @Override
    public PositionEntity save(PositionEntity position) {
        return baseDAO.save(position);
    }

    @Override
    public PositionEntity delete(PositionEntity position) {
        return baseDAO.delete(position);
    }
}
