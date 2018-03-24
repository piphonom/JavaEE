
package ru.otus.rik.web.jaxws.fee.client;

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
@WebServiceClient(name = "FeeWebServiceImplService", targetNamespace = "http://service.fee.jaxws.web.rik.otus.ru/", wsdlLocation = "http://lesha-lenovo-ideapad-y510p:8080/rik-it-system/FeeWebServiceImplService?wsdl")
public class FeeWebServiceImplService
    extends Service
{

    private final static URL FEEWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException FEEWEBSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName FEEWEBSERVICEIMPLSERVICE_QNAME = new QName("http://service.fee.jaxws.web.rik.otus.ru/", "FeeWebServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://lesha-lenovo-ideapad-y510p:8080/rik-it-system/FeeWebServiceImplService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FEEWEBSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        FEEWEBSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public FeeWebServiceImplService() {
        super(__getWsdlLocation(), FEEWEBSERVICEIMPLSERVICE_QNAME);
    }

    public FeeWebServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FEEWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public FeeWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, FEEWEBSERVICEIMPLSERVICE_QNAME);
    }

    public FeeWebServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FEEWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public FeeWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FeeWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FeeWebService
     */
    @WebEndpoint(name = "FeeWebServicePort")
    public FeeWebService getFeeWebServicePort() {
        return super.getPort(new QName("http://service.fee.jaxws.web.rik.otus.ru/", "FeeWebServicePort"), FeeWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FeeWebService
     */
    @WebEndpoint(name = "FeeWebServicePort")
    public FeeWebService getFeeWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.fee.jaxws.web.rik.otus.ru/", "FeeWebServicePort"), FeeWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FEEWEBSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw FEEWEBSERVICEIMPLSERVICE_EXCEPTION;
        }
        return FEEWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
