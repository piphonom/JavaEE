package ru.otus.rik.service.persistence.dao;

import ru.otus.rikapi.entities.InvocationStatisticsEntity;

import java.util.List;

public interface InvocationStatisticsDAO {
    List<InvocationStatisticsEntity> findAll();
    InvocationStatisticsEntity save(InvocationStatisticsEntity statistics);
}
