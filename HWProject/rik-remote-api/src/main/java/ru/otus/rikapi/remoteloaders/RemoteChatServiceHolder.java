package ru.otus.rikapi.remoteloaders;

import ru.otus.rikapi.service.ChatService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteChatServiceHolder {

    private RemoteChatServiceHolder() {
    }

    public static ChatService getChatService() {
        try {
            InitialContext context = new InitialContext();
            ChatService chatService = (ChatService) context.lookup("java:global/rik-it-system/ChatServiceImpl!ru.otus.rikapi.service.ChatService");
            return chatService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
