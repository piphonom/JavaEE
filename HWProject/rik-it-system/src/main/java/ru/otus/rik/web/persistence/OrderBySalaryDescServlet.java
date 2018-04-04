package ru.otus.rik.web.persistence;

import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/OrderBySalaryDesc")
public class OrderBySalaryDescServlet extends HttpServlet {
    PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserEntity> users = persistenceService.findUsersOrderBySalaryDesc();
        String format = "%s : %s : %s";
        try(PrintWriter writer = resp.getWriter()) {
            users.forEach(user -> {
                String line = String.format(format, user.getName(), user.getPositionRef().getTitle(), user.getPositionRef().getSalary());
                writer.println(line);
            });
        }
    }
}
