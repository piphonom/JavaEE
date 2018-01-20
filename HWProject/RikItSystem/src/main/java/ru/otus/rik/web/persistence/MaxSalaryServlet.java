package ru.otus.rik.web.persistence;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MaxSalary")
public class MaxSalaryServlet extends HttpServlet {

    private PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = persistenceService.findUserWithMaxSalary();
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("Max salary has: " + user.getName() + ". And it is " + user.getPositionRef().getSalary());
        }
    }
}
