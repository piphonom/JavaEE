package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.StatisticsEntity;

import java.util.List;

public interface StatisticsDAO {
    StatisticsEntity findById(int id);
    List<StatisticsEntity> findAll();
    StatisticsEntity save(StatisticsEntity statistics);
    StatisticsEntity delete(StatisticsEntity statistics);
}
