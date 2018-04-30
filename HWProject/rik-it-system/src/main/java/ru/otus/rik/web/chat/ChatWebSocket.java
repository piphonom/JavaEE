package ru.otus.rik.web.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Slf4j
public class ChatWebSocket {

    private static final Gson jsonBuilder = new GsonBuilder().create();

    private static Set<Session> sessions = ConcurrentHashMap.newKeySet();

    //@EJB
    @Inject
    private ChatService chatService;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        Principal principal = session.getUserPrincipal();
        ChatMessage chatMessage = chatService.addMessage(principal.getName(), message);
        String result = jsonBuilder.toJson(chatMessage);
        sessions.forEach(s -> {
            try {
                if(!s.isOpen()) {
                    sessions.remove(s);
                    return;
                }
                s.getBasicRemote().sendText(result);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        });
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}
