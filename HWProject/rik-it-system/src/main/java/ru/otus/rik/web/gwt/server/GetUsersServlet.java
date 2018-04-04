package ru.otus.rik.web.gwt.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.web.gwt.shared.model.UserDTO;
import ru.otus.rik.web.gwt.shared.model.UsersListDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetUsers", urlPatterns = "/allUsers")
public class GetUsersServlet extends HttpServlet {
    private static PersistenceService persistenceService = new JpaPersistenceService();
    private static final Gson jsonBuilder = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserEntity> users = persistenceService.findAllUsers();
        UsersListDTO usersListDTO = new UsersListDTO();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setDepartment(user.getDepartmentRef().getName());

            usersListDTO.addUser(userDTO);
        });

        try(PrintWriter writer = resp.getWriter()) {
            String json = jsonBuilder.toJson(usersListDTO);
            writer.println(json);
        }
    }
}
