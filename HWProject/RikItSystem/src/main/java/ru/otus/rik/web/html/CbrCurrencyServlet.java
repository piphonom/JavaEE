package ru.otus.rik.web.html;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.rik.domain.currency.CbrValute;
import ru.otus.rik.domain.currency.CbrValuteList;
import ru.otus.rik.service.xml.XmlBinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "CbrCurrency", urlPatterns = "/currency")
public class CbrCurrencyServlet extends HttpServlet {

    private static final String CBR_CURRENCY_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static final Gson jsonBuilder = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URL url = new URL(CBR_CURRENCY_URL);
        URLConnection urlConnection = url.openConnection();
        CbrValuteList valuteList;
        try (InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
             PrintWriter writer = resp.getWriter()) {
            XmlBinder<CbrValuteList> binder = new XmlBinder<>(CbrValuteList.class);
            valuteList = binder.fromXML(inputStream);
            Map<String, String> currencyValues = valuteList.stream().collect(Collectors.toMap(CbrValute::getCharCode, CbrValute::getValue));
            CurrencyDTO dto = new CurrencyDTO();
            dto.setUsd(currencyValues.get("USD"));
            dto.setEur(currencyValues.get("EUR"));
            dto.setChf(currencyValues.get("CHF"));
            writer.println(jsonBuilder.toJson(dto));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
