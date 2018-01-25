package ru.otus.rik.web.html;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet(name = "RbcNews", urlPatterns = "/rbc")
public class RbcNewsServlet extends HttpServlet {
    private static final String RBC_URL = "https://www.rbc.ru/";
    private static final Gson jsonBuilder = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Document rbcContent = Jsoup.connect(RBC_URL).get();
        Elements news = rbcContent.getElementsByClass("main-feed__item");//.first().getElementsByTag("a");

        RbcNewsDTO dto = new RbcNewsDTO();
        Iterator<Element> newsIterator = news.iterator();
        while(newsIterator.hasNext()) {
            Element event = newsIterator.next().getElementsByTag("a").first();
            if (event.hasAttr("href")) {
                String link = event.attr("href");
                String title = event.getElementsByClass("main-feed__item__title").first().text();
                dto.addEvent(title, link);
            }
        }
        String json = jsonBuilder.toJson(dto);
        resp.setContentType("application/json; charset=UTF8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(json);
        }
    }
}
