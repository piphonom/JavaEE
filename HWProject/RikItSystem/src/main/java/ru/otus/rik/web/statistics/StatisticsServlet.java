package ru.otus.rik.web.statistics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.domain.StatisticsEntity;
import ru.otus.rik.service.helpers.StatisticsServiceHolder;
import ru.otus.rik.service.statistics.ProcessStatisticsException;
import ru.otus.rik.service.statistics.StatisticsData;
import ru.otus.rik.service.statistics.StatisticsDisabledException;
import ru.otus.rik.service.statistics.StatisticsService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

    private final static Gson jsonBuilder = new GsonBuilder().create();

    @EJB
    private StatisticsService statisticsService;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticsService.processOptions(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()) {
            StatisticsData data = StatisticsData.builder()
                    .userTime(req.getParameter("userTime"))
                    .pageName(req.getParameter("page"))
                    .prevId(req.getParameter("prevId"))
                    .clientName(req.getHeader("user-agent"))
                    .clientIP(req.getRemoteAddr())
                    .origin(req.getHeader("origin"))
                    .build();

            int prevId = statisticsService.processStatistics(data);
            StatisticsResponse statisticsResponse = new StatisticsResponse();
            statisticsResponse.setId(String.valueOf(prevId));
            writer.println(jsonBuilder.toJson(statisticsResponse));
        } catch (ProcessStatisticsException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (StatisticsDisabledException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StatisticsEntity> statistics = statisticsService.getAllStatistics();
        req.setAttribute("statistics", statistics);
        req.getRequestDispatcher("/WEB-INF/classes/ftl/statistics.ftl").forward(req, resp);
    }

    @Setter
    @Getter
    private static class StatisticsResponse {
        private String id;
    }
}
