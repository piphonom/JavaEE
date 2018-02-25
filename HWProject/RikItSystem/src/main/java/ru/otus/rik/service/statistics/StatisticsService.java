package ru.otus.rik.service.statistics;

import ru.otus.rik.domain.StatisticsEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatisticsService {
    boolean getEnabled();
    int processStatistics(HttpServletRequest request) throws ProcessStatisticsException, StatisticsDisabledException;
    List<StatisticsEntity> getAllStatistics();
 }
