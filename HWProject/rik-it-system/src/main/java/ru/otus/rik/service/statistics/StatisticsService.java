package ru.otus.rik.service.statistics;

import ru.otus.rikapi.entities.StatisticsEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StatisticsService {
    boolean getEnabled();
    int processStatistics(StatisticsData data) throws ProcessStatisticsException, StatisticsDisabledException;
    List<StatisticsEntity> getAllStatistics();
    void addListener(StatisticsListener listener);
    void removeListener(StatisticsListener listener) throws StatisticsListenerNotFoundException;
    /* for CORS */
    void processOptions(HttpServletRequest request, HttpServletResponse response);
 }
