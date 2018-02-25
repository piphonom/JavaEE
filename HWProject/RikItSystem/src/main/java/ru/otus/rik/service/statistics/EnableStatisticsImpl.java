package ru.otus.rik.service.statistics;

public class EnableStatisticsImpl implements EnableStatisticsMBean {
    private volatile boolean enabled = true;

    @Override
    public void setEnabled(boolean isEnabled) {
        this.enabled = isEnabled;
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }
}
