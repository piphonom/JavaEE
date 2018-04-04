package ru.otus.rik.service.persistence.dao;

import ru.otus.rikapi.entities.StatisticsEntity;

import java.util.List;

public interface StatisticsDAO {
    StatisticsEntity findById(int id);
    List<StatisticsEntity> findAll();
    StatisticsEntity save(StatisticsEntity statistics);
    StatisticsEntity delete(StatisticsEntity statistics);
}
