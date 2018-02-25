package ru.otus.rik.service.statistics;

public class StatisticsDisabledException extends Exception {
    public StatisticsDisabledException() {
    }

    public StatisticsDisabledException(String message) {
        super(message);
    }
}
