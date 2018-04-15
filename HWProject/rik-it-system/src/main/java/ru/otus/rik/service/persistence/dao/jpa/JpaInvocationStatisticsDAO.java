package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.service.persistence.dao.InvocationStatisticsDAO;
import ru.otus.rikapi.entities.InvocationStatisticsEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaInvocationStatisticsDAO implements InvocationStatisticsDAO {

    private final JpaBaseDAO<InvocationStatisticsEntity> baseDAO;

    public JpaInvocationStatisticsDAO(EntityManager entityManager) {
        baseDAO = new JpaBaseDAO<>(entityManager, InvocationStatisticsEntity.class);
    }

    @Override
    public List<InvocationStatisticsEntity> findAll() {
        return baseDAO.findAll();
    }

    @Override
    public InvocationStatisticsEntity save(InvocationStatisticsEntity statistics) {
        return baseDAO.save(statistics);
    }
}
