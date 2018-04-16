package ru.otus.rik.web.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
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

    private static Session session;

    @Inject
    private ChatService chatService;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        //this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        ChatMessage chatMessage = chatService.addMessage(message);
    }

    public void onChatMessageEvent(@Observes ChatMessage chatMessage) {
        String result = jsonBuilder.toJson(chatMessage);
        /*if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(result);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }*/
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
        //this.session = null;
    }
}
