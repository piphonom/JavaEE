package ru.otus.rik.web.jsp;

import com.google.common.cache.Cache;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.persistence.SearchParams;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.otus.rik.web.listener.ServletRequestAttributeListenerImpl.SEARCH_CACHE_ATTRIBUTE_NAME;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    public static final String SEARCH_RESULT_ATTRIBUTE_NAME = "searchParams";

    private static final String USERS_JSP = "/users.jsp";

    private static final PersistenceService persistenceService = new JpaPersistenceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String location = request.getParameter("location");

        SearchParams searchParams = new SearchParams(name, department, location);
        List<UserEntity> usersList = findInCache(searchParams);
        if (usersList == null)
            usersList = persistenceService.findUsersByParams(searchParams);

        request.setAttribute("usersList", usersList);
        SearchResultWrapper wrapper = new SearchResultWrapper(searchParams, usersList);
        request.setAttribute(SEARCH_RESULT_ATTRIBUTE_NAME, wrapper);
        request.getRequestDispatcher(request.getContextPath() + USERS_JSP).forward(request, response);
    }

    // TODO: move Cache to facade class
    private List<UserEntity> findInCache(SearchParams params) {
        ServletContext context = this.getServletContext();

        @SuppressWarnings("unchecked cast")
        Cache<String, List<UserEntity>> cache = (Cache<String, List<UserEntity>>) context.getAttribute(SEARCH_CACHE_ATTRIBUTE_NAME);

        if (cache != null)
            return cache.getIfPresent(params.toString());

        return null;
    }

}
