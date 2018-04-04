package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;
import ru.otus.rik.service.helpers.RemoteChatServiceHolder;

import java.util.Queue;

@Getter
public class GetChat extends ActionSupport {

    private Queue<ChatMessage> messages;
    private ChatService chatService = RemoteChatServiceHolder.getChatService();

    @Override
    public String execute() throws Exception {
        messages = chatService.getMessages();
        return SUCCESS;
    }
}
