package ru.otus.rik.web.html;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class RbcNewsDTO {

    List<Event> news = new LinkedList<>();

    public void addEvent(String title, String link) {
        Event event = new Event();
        event.setTitle(title);
        event.setLink(link);
        news.add(event);
    }

    @Getter
    @Setter
    public static class Event {
        String title;
        String link;
    }
}
