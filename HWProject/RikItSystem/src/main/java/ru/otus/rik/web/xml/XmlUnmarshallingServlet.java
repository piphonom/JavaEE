package ru.otus.rik.web.xml;

import org.xml.sax.SAXException;
import ru.otus.rik.service.json.JsonBinder;
import ru.otus.rik.service.xml.XmlBinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

@WebServlet("/XmlUnmarshalAllUsers")
public class XmlUnmarshallingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URL xsd = getServletContext().getResource(Constants.SCHEMA_RESOURCE);
        try {
            File usersFile = new File(Constants.XML_ALL_USERS_FILE);
            XmlBinder<UserEntitiesList> binder = new XmlBinder<>(UserEntitiesList.class);
            try(PrintWriter respWriter = resp.getWriter()) {
                UserEntitiesList users = binder.fromXML(xsd, usersFile);
                String json = new JsonBinder<>(UserEntitiesList.class).toJson(users);
                try(PrintWriter fileWriter = new PrintWriter(Constants.JSON_ALL_USERS_FILE)) {
                    fileWriter.println(json);
                }
                respWriter.println(json);
            } catch (JAXBException | SAXException  e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
