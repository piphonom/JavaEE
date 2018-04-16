package ru.otus.rik.service.chat;

import ru.otus.rik.service.json.JsonBinder;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.StringReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//@Remote(ChatService.class)
//@LocalBean
//@Stateless
@ApplicationScoped
public class ChatServiceImpl implements ChatService {

    private static final JsonBinder<ChatMessage> jsonBinder = new JsonBinder<>(ChatMessage.class);
    private Queue<ChatMessage> messages = new ConcurrentLinkedQueue<>();

    @Inject
    @Any
    private Event<ChatMessage> chatEvent;

    @Override
    public Queue<ChatMessage> getMessages() {
        return messages;
    }

    @Override
    public ChatMessage addMessage(String message) {
        ChatMessage chatMessage = jsonBinder.fromJson(new StringReader(message));
        messages.add(chatMessage);

        chatEvent.fire(chatMessage);

        return chatMessage;
    }
}
