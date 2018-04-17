package ru.otus.rik.service.chat;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class WebSocketSessionsStoreImpl implements WebSocketSessionsStore {

    private Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void addSession(Session session) {
        sessions.add(session);
    }

    @Override
    public boolean removeSession(Session session) {
        return sessions.remove(session);
    }

    @Override
    public Set<Session> getSessions() {
        return new HashSet<>(sessions);
    }
}
