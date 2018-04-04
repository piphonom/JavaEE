package ru.otus.rik.web.xml;

import ru.otus.rik.service.json.JsonBinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/JsonUnmarshalOddUsers")
public class JsonUnmarshallingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (FileReader fileReader = new FileReader(Constants.JSON_ALL_USERS_FILE)) {
            JsonBinder<UserEntitiesList> binder = new JsonBinder<>(UserEntitiesList.class);
            UserEntitiesList users = binder.fromJson(fileReader);
            try (PrintWriter respWriter = resp.getWriter()) {
                for (int i = 0; i < users.size(); i++) {
                    if (i % 2 != 0)
                        respWriter.println(users.get(i).getName());
                }
            }
        }
    }
}
