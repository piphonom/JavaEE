package ru.otus.rik.service.search;

import com.google.common.cache.Cache;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.persistence.SearchParams;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Stateless
@Local(SearchService.class)
public class SearchServiceImpl implements SearchService {
    public static final String SEARCH_RESULT_ATTRIBUTE_NAME = "searchParams";
    public static final String SEARCH_CACHE_ATTRIBUTE_NAME = "searchCache";

    @EJB
    private PersistenceService persistenceService;

    @Override
    public List<UserEntity> findUserByParams(HttpServletRequest request) {
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String location = request.getParameter("location");

        SearchParams searchParams = new SearchParams(name, department, location);
        List<UserEntity> usersList = findInCache(request.getServletContext(), searchParams);
        if (usersList == null)
            usersList = persistenceService.findUsersByParams(searchParams);

        SearchResultWrapper wrapper = new SearchResultWrapper(searchParams, usersList);
        request.setAttribute(SEARCH_RESULT_ATTRIBUTE_NAME, wrapper);

        return usersList;
    }

    private List<UserEntity> findInCache(ServletContext servletContext, SearchParams params) {
        @SuppressWarnings("unchecked cast")
        Cache<SearchParams, List<UserEntity>> cache = (Cache<SearchParams, List<UserEntity>>) servletContext.getAttribute(SEARCH_CACHE_ATTRIBUTE_NAME);

        if (cache != null)
            return cache.getIfPresent(params);

        return null;
    }

}
