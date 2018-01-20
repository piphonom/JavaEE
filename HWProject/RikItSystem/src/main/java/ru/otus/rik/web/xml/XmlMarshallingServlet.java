package ru.otus.rik.web.xml;

import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.xml.XmlBinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/XmlMarshalAllUsers")
public class XmlMarshallingServlet extends HttpServlet {
    private static final PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntitiesList usersList = new UserEntitiesList(persistenceService.findAllUsers());
        XmlBinder<UserEntitiesList> binder = new XmlBinder<>(UserEntitiesList.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PrintWriter responseWriter = resp.getWriter()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(Constants.XML_ALL_USERS_FILE))) {
                binder.toXML(usersList, byteArrayOutputStream);
                binder.toXML(usersList, fileOutputStream);
                responseWriter.println(byteArrayOutputStream.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }
}
