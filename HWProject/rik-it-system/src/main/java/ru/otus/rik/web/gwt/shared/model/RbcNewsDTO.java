package ru.otus.rik.web.gwt.shared.model;

import java.util.LinkedList;
import java.util.List;

public class RbcNewsDTO  {

    List<Event> news = new LinkedList<>();

    public void setNews(List<RbcNewsDTO.Event> news) {
        this.news = news;
    }

    public List<RbcNewsDTO.Event> getNews() {
        return news;
    }

    public void addEvent(String title, String link) {
        Event event = new Event();
        event.setTitle(title);
        event.setLink(link);
        news.add(event);
    }



    public static class Event {
        String title;
        String link;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }
    }
}
