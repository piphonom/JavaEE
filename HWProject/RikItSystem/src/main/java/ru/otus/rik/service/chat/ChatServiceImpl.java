package ru.otus.rik.service.chat;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.AuthenticationServiceHolder;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatServiceImpl implements ChatService {

    private Queue<ChatMessage> messages = new ConcurrentLinkedQueue<>();

    @Override
    public Queue<ChatMessage> getMessages() {
        return messages;
    }

    @Override
    public ChatMessage addMessage(String message) {
        UserEntity currentUser = AuthenticationServiceHolder.getAuthenticationService().getCurrentUser();

        if (currentUser == null)
            return null;

        ChatMessage chatMessage = new ChatMessage(currentUser.getName(), message);
        messages.add(chatMessage);

        return chatMessage;
    }
}
