package ru.otus.rik.web.html;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Nashorn", urlPatterns = "/nashorn")
public class NashornServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        try(PrintWriter writer = resp.getWriter()) {
            String script = req.getParameter("toExecute");
            if (script != null) {
                try {
                    Object result = engine.eval(script);
                    writer.println(result);
                } catch (ScriptException e) {
                    throw new ServletException("Execution failure", e);
                }
            }
        }
    }
}
