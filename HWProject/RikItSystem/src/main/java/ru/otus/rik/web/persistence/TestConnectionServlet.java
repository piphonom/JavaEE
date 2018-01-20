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

@WebServlet("/TestConnection")
public class TestConnectionServlet extends HttpServlet {
    /* not thread safe, just for test */
    private static String nameToFind = "Bob Marley";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersistenceService persistenceService = new JpaPersistenceService();
        UserEntity user = persistenceService.findUserByName(nameToFind);
        if (user == null) {
            nameToFind = new StringBuffer(nameToFind).reverse().toString();
        }
        user = persistenceService.findUserByName(nameToFind);
        try (PrintWriter responseWriter = resp.getWriter()) {
            if (user != null) {
                responseWriter.println(user.getName() + " : " + user.getEmail());
                user.setName(new StringBuffer(user.getName()).reverse().toString());
                persistenceService.saveUser(user);
                nameToFind = user.getName();
            } else {
                responseWriter.println("Nothing was found");
            }
        }
    }
}
