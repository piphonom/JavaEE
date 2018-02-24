package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.StatisticsEntity;

public interface StatisticsDAO {
    StatisticsEntity findById(int id);
    StatisticsEntity save(StatisticsEntity statistics);
    StatisticsEntity delete(StatisticsEntity statistics);
}
