package ru.otus.rik.service.statistics;

import ru.otus.rikapi.entities.StatisticsEntity;

import java.util.List;

public interface StatisticsListener {
    void onUpdateStatistics(List<StatisticsEntity> statistics);
}
