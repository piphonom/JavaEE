package ru.otus.rik.service.chat;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.AuthenticationServiceHolder;
import ru.otus.rik.service.json.JsonBinder;

import java.io.StringReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatServiceImpl implements ChatService {

    private static final JsonBinder<ChatMessage> jsonBinder = new JsonBinder<>(ChatMessage.class);
    private Queue<ChatMessage> messages = new ConcurrentLinkedQueue<>();

    @Override
    public Queue<ChatMessage> getMessages() {
        return messages;
    }

    @Override
    public ChatMessage addMessage(String message) {
        ChatMessage chatMessage = jsonBinder.fromJson(new StringReader(message));
        messages.add(chatMessage);

        return chatMessage;
    }
}
