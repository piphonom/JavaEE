
package ru.otus.rik.web.jaxws.salary.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RikSalaryWebService", targetNamespace = "http://service.salary.jaxws.web.rik.otus.ru/", wsdlLocation = "http://lesha-lenovo-ideapad-y510p:8080/rik-it-system/RikSalaryWebService?wsdl")
public class RikSalaryWebService
    extends Service
{

    private final static URL RIKSALARYWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException RIKSALARYWEBSERVICE_EXCEPTION;
    private final static QName RIKSALARYWEBSERVICE_QNAME = new QName("http://service.salary.jaxws.web.rik.otus.ru/", "RikSalaryWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://lesha-lenovo-ideapad-y510p:8080/rik-it-system/RikSalaryWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RIKSALARYWEBSERVICE_WSDL_LOCATION = url;
        RIKSALARYWEBSERVICE_EXCEPTION = e;
    }

    public RikSalaryWebService() {
        super(__getWsdlLocation(), RIKSALARYWEBSERVICE_QNAME);
    }

    public RikSalaryWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), RIKSALARYWEBSERVICE_QNAME, features);
    }

    public RikSalaryWebService(URL wsdlLocation) {
        super(wsdlLocation, RIKSALARYWEBSERVICE_QNAME);
    }

    public RikSalaryWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RIKSALARYWEBSERVICE_QNAME, features);
    }

    public RikSalaryWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RikSalaryWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RikSalaryService
     */
    @WebEndpoint(name = "RikSalaryServicePort")
    public RikSalaryService getRikSalaryServicePort() {
        return super.getPort(new QName("http://service.salary.jaxws.web.rik.otus.ru/", "RikSalaryServicePort"), RikSalaryService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RikSalaryService
     */
    @WebEndpoint(name = "RikSalaryServicePort")
    public RikSalaryService getRikSalaryServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.salary.jaxws.web.rik.otus.ru/", "RikSalaryServicePort"), RikSalaryService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RIKSALARYWEBSERVICE_EXCEPTION!= null) {
            throw RIKSALARYWEBSERVICE_EXCEPTION;
        }
        return RIKSALARYWEBSERVICE_WSDL_LOCATION;
    }

}
