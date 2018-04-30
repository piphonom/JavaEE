package ru.otus.rik.service.chat;

import ru.otus.rik.service.json.JsonBinder;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.io.StringReader;
import java.security.Principal;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//@Remote(ChatService.class)
//@LocalBean
//@Stateless
@ApplicationScoped
public class ChatServiceImpl implements ChatService {

    private static final JsonBinder<ChatMessage> jsonBinder = new JsonBinder<>(ChatMessage.class);
    private Queue<ChatMessage> messages = new ConcurrentLinkedQueue<>();

    @Override
    public Queue<ChatMessage> getMessages() {
        return messages;
    }

    @Override
    public ChatMessage addMessage(String user, String message) {
        ChatMessage chatMessage = jsonBinder.fromJson(new StringReader(message));
        chatMessage.setUser(user);
        messages.add(chatMessage);

        return chatMessage;
    }
}
