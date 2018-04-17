package ru.otus.rik.service.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.rikapi.service.ChatMessage;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

@Dependent
public class ChatMessageHandlerImpl implements ChatMessageHandler, Serializable {

    @Inject
    private WebSocketSessionsStore webSocketSessionsStore;

    @Override
    public void handleMessage(@Observes ChatMessage message) {
        Gson jsonBuilder = new GsonBuilder().create();
        String result = jsonBuilder.toJson(message);
        webSocketSessionsStore.getSessions().forEach( session -> {
                if (session != null && session.isOpen()) {
                    try {
                        session.getBasicRemote().sendText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}
