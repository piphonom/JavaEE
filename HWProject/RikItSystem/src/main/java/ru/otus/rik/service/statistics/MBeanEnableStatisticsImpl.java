package ru.otus.rik.service.statistics;

public class MBeanEnableStatisticsImpl implements MBeanEnableStatistics {
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
