package ru.otus.rik.web.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.otus.rik.service.chat.ChatMessage;
import ru.otus.rik.service.helpers.ChatServiceHolder;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Slf4j
public class ChatWebSocket {

    private static final Gson jsonBuilder = new GsonBuilder().create();

    private static Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        ChatMessage chatMessage = ChatServiceHolder.getChatService().addMessage(message);
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
