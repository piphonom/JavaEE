package ru.otus.rikapi.service;

import java.util.Queue;

public interface ChatService {
    Queue<ChatMessage> getMessages();
    ChatMessage addMessage(String message);
}
