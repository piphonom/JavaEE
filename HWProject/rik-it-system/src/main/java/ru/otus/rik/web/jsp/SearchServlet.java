package ru.otus.rik.web.jsp;

import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.search.SearchService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    private static final String USERS_JSP = "/search-result.jsp";

    @EJB
    private SearchService searchService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserEntity> usersList = searchService.findUserByParams(request);

        request.getSession(false).setAttribute("usersList", usersList);

        request.getRequestDispatcher(USERS_JSP).forward(request, response);
    }
}
