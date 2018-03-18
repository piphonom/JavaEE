package ru.otus.rik.web.filter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import ru.otus.rik.service.helpers.RandomString;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebFilter(filterName = "CsrfGenerateFilter", urlPatterns = "/*")
public class CsrfGenerateFilter implements Filter {

    private final static int CACHE_CAPACITY = 1000;
    private final static long CSRF_LIFETIME_MINUTES = 10;
    public final static String CACHE_ATTRIBUTE_NAME = "csrfCache";
    public final static String CSRF_ATTRIBUTE_NAME = "csrf";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        @SuppressWarnings("unchecked cast")
        Cache<String, Boolean> csrfCache = (Cache<String, Boolean>) session.getAttribute(CACHE_ATTRIBUTE_NAME);
        if (csrfCache == null) {
            csrfCache = CacheBuilder.newBuilder()
                    .initialCapacity(CACHE_CAPACITY)
                    .expireAfterWrite(CSRF_LIFETIME_MINUTES, TimeUnit.MINUTES)
                    .build();

            session.setAttribute(CACHE_ATTRIBUTE_NAME, csrfCache);
        }

        String csrf = new RandomString().nextString();
        csrfCache.put(csrf, Boolean.TRUE);

        httpRequest.setAttribute(CSRF_ATTRIBUTE_NAME, csrf);

        /* session control */
        String path =  httpRequest.getServletPath();
        if (!path.endsWith("/login") && !path.endsWith("/login.jsp")
            && !path.endsWith(".css") && !path.endsWith(".js")) {
            session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                ((HttpServletResponse) resp).sendRedirect(httpRequest.getContextPath() + "/login.jsp");
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
