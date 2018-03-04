package ru.otus.rik.service.statistics;

import ru.otus.rik.domain.StatisticsEntity;

import java.util.List;

public interface StatisticsListener {
    void onUpdateStatistics(List<StatisticsEntity> statistics);
}
