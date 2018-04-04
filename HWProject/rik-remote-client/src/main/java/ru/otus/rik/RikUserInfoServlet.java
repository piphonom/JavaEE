package ru.otus.rik;

import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.remoteloaders.RemoteUserServiceHolder;
import ru.otus.rikapi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserInfoServlet", urlPatterns = "/info")
public class RikUserInfoServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = RemoteUserServiceHolder.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            List<UserEntity> users = userService.findAllUsers();
            writer.println("Registered users in Rik Corp: ");
            users.forEach(user -> writer.println(String.format("Name: %s, emaile: %s", user.getName(), user.getEmail())));
            writer.println("");
            writer.println("Average salary in Rik Corp is: " + userService.getAverageSalary());
        }
    }
}
