package ru.otus.rik.web.chat;

import lombok.extern.slf4j.Slf4j;
import ru.otus.rik.service.chat.WebSocketSessionsStore;
import ru.otus.rikapi.service.ChatService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

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
        chatService.addMessage(message);
    }

    @OnClose
    public void onClose(Session session) {
        webSocketSessionsStore.removeSession(session);
    }
}
