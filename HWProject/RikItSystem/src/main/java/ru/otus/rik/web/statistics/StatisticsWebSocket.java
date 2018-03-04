package ru.otus.rik.web.statistics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.helpers.StatisticsServiceHolder;
import ru.otus.rik.service.statistics.StatisticsListenerNotFoundException;
import ru.otus.rik.service.statistics.StatisticsListener;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@ServerEndpoint("/statistics")
@Slf4j
public class StatisticsWebSocket implements StatisticsListener {

    private final static Gson jsonBuilder = new GsonBuilder().create();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        StatisticsServiceHolder.getStatisticsService().addListener(this);
    }

    @OnMessage
    public void processMessage(Session session, String message) {

    }

    @OnClose
    public void onClose(Session session) {
        try {
            StatisticsServiceHolder.getStatisticsService().removeListener(this);
        } catch (StatisticsListenerNotFoundException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public void onUpdateStatistics(List<StatisticsEntity> statistics) {
        if (session != null) {
            try {
                if (!session.isOpen()) {
                    StatisticsServiceHolder.getStatisticsService().removeListener(this);
                    return;
                }
                this.session.getBasicRemote().sendText(jsonBuilder.toJson(statistics));
            } catch (IOException | StatisticsListenerNotFoundException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }
}
