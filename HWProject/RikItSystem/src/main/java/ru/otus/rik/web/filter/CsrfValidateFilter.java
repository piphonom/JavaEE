package ru.otus.rik.web.filter;

import com.google.common.cache.Cache;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CsrfValidateFilter", urlPatterns = {"/login", "/users"})
public class CsrfValidateFilter implements Filter {

    private static final String CSRF_REQUEST_PARAMETER_NAME = "csrf";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String csrf = (String) httpRequest.getParameter(CSRF_REQUEST_PARAMETER_NAME);
        if (csrf == null) {
            ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        HttpSession session = httpRequest.getSession();
        @SuppressWarnings("unchecked cast")
        Cache<String, Boolean> csrfCache = (Cache<String, Boolean>) session.getAttribute(CsrfGenerateFilter.CACHE_ATTRIBUTE_NAME);

        if (csrfCache != null && csrfCache.getIfPresent(csrf) != null) {
            chain.doFilter(req, resp);
        }

        ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void destroy() {

    }
}
