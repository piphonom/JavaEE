package ru.otus.rik.service.helpers;

import ru.otus.rik.service.statistics.StatisticsService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteStatisticsServiceHolder {

    private RemoteStatisticsServiceHolder() {
    }

    public static StatisticsService getStatisticsService() {
        try {
            InitialContext context = new InitialContext();
            StatisticsService statisticsService = (StatisticsService) context.lookup("java:global/rik-it-system/StatisticsServiceImpl!ru.otus.rik.service.statistics.StatisticsService");
            return statisticsService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
