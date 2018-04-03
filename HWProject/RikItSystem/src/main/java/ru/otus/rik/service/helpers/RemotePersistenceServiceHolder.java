package ru.otus.rik.service.helpers;

import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class RemotePersistenceServiceHolder {

    private RemotePersistenceServiceHolder() {
    }

    public static PersistenceService getPersistenceService() {
        try {
            InitialContext context = new InitialContext();
            PersistenceService persistenceService = (PersistenceService) context.lookup("java:global/rik-it-system/JpaPersistenceService!ru.otus.rik.service.persistence.PersistenceService");
            return persistenceService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
