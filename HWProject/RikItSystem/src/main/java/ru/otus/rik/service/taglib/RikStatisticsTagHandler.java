package ru.otus.rik.service.taglib;

import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RikStatisticsTagHandler extends SimpleTagSupport {

    private final String GET_STATISTICS_JS_TEMPLATE =
            "<script type=\"text/javascript\" src=\"/js/statistics.js\"></script>\n" +
            "<script lang=\"javascript\">\n" +
            "setTimeout(function () { \n" +
            "        new getStatistics(\"%s\");\n" +
            "    }, 0); \n" +
            "</script>";

    @Override
    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext)getJspContext();
        String name = ((HttpServletRequest)context.getRequest()).getServletPath();
        String jsCodeSnippet = String.format(GET_STATISTICS_JS_TEMPLATE, name);
        getJspContext().getOut().print(jsCodeSnippet);
    }
}
