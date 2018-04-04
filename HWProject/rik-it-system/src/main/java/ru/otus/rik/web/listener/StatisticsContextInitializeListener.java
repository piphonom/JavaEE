package ru.otus.rik.web.listener;

import ru.otus.rik.service.helpers.RemoteStatisticsServiceHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StatisticsContextInitializeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* To initialize MBean */
        RemoteStatisticsServiceHolder.getStatisticsService();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
