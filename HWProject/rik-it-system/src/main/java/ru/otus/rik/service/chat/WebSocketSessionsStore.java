package ru.otus.rik.service.chat;

import javax.websocket.Session;
import java.util.Set;

public interface WebSocketSessionsStore {
    void addSession(Session session);
    boolean removeSession(Session session);
    Set<Session> getSessions();
}
