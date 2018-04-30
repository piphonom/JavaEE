package ru.otus.rik.service.chat;

import ru.otus.rikapi.service.ChatMessage;

import javax.enterprise.event.Observes;

public interface ChatMessageHandler {
    void handleMessage(@Observes ChatMessage message);
}
