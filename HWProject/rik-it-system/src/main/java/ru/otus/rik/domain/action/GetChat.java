package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import ru.otus.rikapi.service.ChatMessage;
import ru.otus.rikapi.service.ChatService;

import javax.inject.Inject;
import java.util.Queue;

@Getter
public class GetChat extends ActionSupport {

    private Queue<ChatMessage> messages;

    @Inject
    private ChatService chatService;

    @Override
    public String execute() throws Exception {
        messages = chatService.getMessages();
        return SUCCESS;
    }
}
