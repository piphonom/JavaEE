package ru.otus.rik.web.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;

@WebServlet("/XPath")
public class XpathServlet extends HttpServlet {
    private static final PersistenceService persistenceService = new JpaPersistenceService();
    private static int tabsCount = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int averageSalary = (int)persistenceService.getAverageSalary();

        try (PrintWriter writer = resp.getWriter()) {
            try (FileInputStream inputStream = new FileInputStream(new File(Constants.XML_ALL_USERS_FILE))) {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document xmlDocument = builder.parse(inputStream);
                XPath xPath = XPathFactory.newInstance().newXPath();
                String expression = "//User[Position[salary > " + averageSalary + "]]";
                NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    writer.println("");
                    parseNode(writer, node);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseNode(PrintWriter writer, Node node) {
        String name = node.getNodeName();

        for (int i = 0; i < tabsCount; i++) {
            writer.append('\t');
        }
        writer.println((name != null) ? name : "");

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                tabsCount++;
                parseNode(writer, currentNode);
                tabsCount--;
            }
        }
    }
}
