package ru.otus.rik.web.jsp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.persistence.helpers.PersistenceServiceHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

    private final static int MAX_CLIENT_NAME_SIZE = 49;
    private final static String DEFAULT_MAKER_NAME = "statMarker";
    private final static String MARKER_ENV_NAME = "STAT_MARKER_NAME";

    private final static Gson jsonBuilder = new GsonBuilder().create();

    private final static String DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String markerName = System.getenv(MARKER_ENV_NAME);
        String userTime = req.getParameter("userTime");
        String pageName = req.getParameter("page");
        String prevId = req.getParameter("prevId");
        String clientName = req.getHeader("user-agent");
        String clientIP = req.getRemoteAddr();

        String serverTime = dateFormat.format(Calendar.getInstance().getTime());

        StatisticsEntity statistics = new StatisticsEntity();
        statistics.setMarkerName(markerName != null ? markerName : DEFAULT_MAKER_NAME);
        statistics.setPageName(pageName);
        statistics.setClientName(clientName.substring(0, MAX_CLIENT_NAME_SIZE));
        statistics.setClientIP(clientIP);

        statistics.setClientTime(userTime);
        statistics.setServerTime(serverTime);

        try {
            statistics.setPrevIdStat(Integer.valueOf(prevId));
        } catch (NumberFormatException e) {}

        StatisticsEntity saved = PersistenceServiceHolder.getPersistenceService().saveStatistics(statistics);

        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setId(String.valueOf(saved.getIdStat()));
        try(PrintWriter writer = resp.getWriter()) {
            writer.println(jsonBuilder.toJson(statisticsResponse));
        }
    }

    @Setter
    @Getter
    private static class StatisticsResponse {
        private String id;
    }
}
