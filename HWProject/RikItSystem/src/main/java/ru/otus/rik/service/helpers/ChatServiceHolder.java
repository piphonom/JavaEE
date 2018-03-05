package ru.otus.rik.service.helpers;

import ru.otus.rik.service.chat.ChatService;
import ru.otus.rik.service.chat.ChatServiceImpl;

public class ChatServiceHolder {
    private static final ChatService chatService = new ChatServiceImpl();

    private ChatServiceHolder() {
    }

    public static ChatService getChatService() {
        return chatService;
    }
}
