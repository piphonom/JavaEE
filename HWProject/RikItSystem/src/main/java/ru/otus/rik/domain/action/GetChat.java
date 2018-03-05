package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import ru.otus.rik.service.chat.ChatMessage;
import ru.otus.rik.service.helpers.ChatServiceHolder;

import java.util.Queue;

@Getter
public class GetChat extends ActionSupport {

    private Queue<ChatMessage> messages;

    @Override
    public String execute() throws Exception {
        messages = ChatServiceHolder.getChatService().getMessages();
        return SUCCESS;
    }
}
