package ru.otus.rik.service.helpers;

import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;

public final class PersistenceServiceHolder {
    private static final PersistenceService persistenceService = new JpaPersistenceService();

    private PersistenceServiceHolder() {
    }

    public static PersistenceService getPersistenceService() {
        return persistenceService;
    }
}
