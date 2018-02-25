package ru.otus.rik.service.helpers;

import ru.otus.rik.service.statistics.StatisticsService;
import ru.otus.rik.service.statistics.StatisticsServiceImpl;

public class StatisticsServiceHolder {

    private final static StatisticsService statisticsService = new StatisticsServiceImpl();

    private StatisticsServiceHolder() {
    }

    public static StatisticsService getStatisticsService() {
        return statisticsService;
    }
}
