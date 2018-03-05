package ru.otus.rik.service.chat;

import java.util.Queue;

public interface ChatService {
    Queue<ChatMessage> getMessages();
    ChatMessage addMessage(String message);
}
