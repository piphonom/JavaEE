package ru.otus.rik.service.helpers;

import ru.otus.rik.service.chat.ChatService;
import ru.otus.rik.service.chat.ChatServiceImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ChatServiceHolder {

    private ChatServiceHolder() {
    }

    public static ChatService getChatService() {
        try {
            InitialContext context = new InitialContext();
            ChatService chatService = (ChatService) context.lookup("java:global/rik-it-system/ChatServiceImpl!ru.otus.rik.service.chat.ChatService");
            return chatService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
