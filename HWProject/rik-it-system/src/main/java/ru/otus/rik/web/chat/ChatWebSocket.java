package ru.otus.rik.web.chat;

import lombok.extern.slf4j.Slf4j;
import ru.otus.rik.service.chat.WebSocketSessionsStore;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;

@ServerEndpoint("/chat")
@Slf4j
public class ChatWebSocket {

    @Inject
    private ChatService chatService;

    @Inject
    private WebSocketSessionsStore webSocketSessionsStore;

    @OnOpen
    public void onOpen(Session session) {
        webSocketSessionsStore.addSession(session);

    }

    @OnMessage
    public void onMessage(Session session, String message) {

        Principal principal = session.getUserPrincipal();
        ChatMessage chatMessage = chatService.addMessage(principal.getName(), message);
    }

    @OnClose
    public void onClose(Session session) {
        webSocketSessionsStore.removeSession(session);
    }
}
