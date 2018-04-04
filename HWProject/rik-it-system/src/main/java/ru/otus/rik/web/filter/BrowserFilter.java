package ru.otus.rik.web.filter;

import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.service.json.JsonBinder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "BrowserFilter", urlPatterns = "/login.jsp")
public class BrowserFilter implements Filter {

    private static ListOfSupportedBrowser browsers;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String userAgent = httpRequest.getHeader("user-agent");
        String[] agents = userAgent.split(" ");

        for (String agent : agents) {
            if (!checkAgent(agent, browsers)) {
                httpRequest.setAttribute("browsersList", browsers);
                httpRequest.getRequestDispatcher(httpRequest.getContextPath() + "/browser.jsp").forward(req, resp);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        JsonBinder<ListOfSupportedBrowser> binder = new JsonBinder<>(ListOfSupportedBrowser.class);
        InputStream browsersStream = config.getServletContext().getResourceAsStream("/WEB-INF/supported-browsers.json");
        browsers = binder.fromJson(new InputStreamReader(browsersStream));
    }

    private boolean checkAgent(String rawAgent,  ListOfSupportedBrowser supportedBrowsers) {
        String agentName = rawAgent.split("/")[0];
        for (SupportedBrowser sbi : supportedBrowsers) {
            if (sbi.name.equals(agentName)) {
                String v = rawAgent.split("/")[1].split("\\.")[0];
                int agentVersion = Integer.parseInt(v);
                if (agentVersion < sbi.minVersion)
                    return false;
            }
        }
        return true;
    }

    @Getter
    @Setter
    public static final class SupportedBrowser {
        private String name;
        private int minVersion;
        private String imageSource;
        private String link;
    }

    @Getter
    @Setter
    public static final class ListOfSupportedBrowser extends ArrayList<SupportedBrowser> {
        List<SupportedBrowser> supportedBrowsers;
    }
}
