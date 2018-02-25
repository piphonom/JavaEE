package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.persistence.dao.StatisticsDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaStatisticsDAO implements StatisticsDAO {

    private final JpaBaseDAO<StatisticsEntity> baseDAO;

    public JpaStatisticsDAO(EntityManager entityManager) {
        baseDAO = new JpaBaseDAO<>(entityManager, StatisticsEntity.class);
    }

    @Override
    public StatisticsEntity findById(int id) {
        return baseDAO.findSingleResultByOneParameter("idStat", String.valueOf(id));
    }

    @Override
    public List<StatisticsEntity> findAll() {
        return baseDAO.findAll();
    }

    @Override
    public StatisticsEntity save(StatisticsEntity statistics) {
        return baseDAO.save(statistics);
    }

    @Override
    public StatisticsEntity delete(StatisticsEntity statistics) {
        return baseDAO.delete(statistics);
    }
}
