
package ru.otus.rik.web.jaxws.fee.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.otus.rik.web.jaxws.fee.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CalculateFeeForProfit_QNAME = new QName("http://service.fee.jaxws.web.rik.otus.ru/", "calculateFeeForProfit");
    private final static QName _CalculateFeeForProfitResponse_QNAME = new QName("http://service.fee.jaxws.web.rik.otus.ru/", "calculateFeeForProfitResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.otus.rik.web.jaxws.fee.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateFeeForProfitResponse }
     * 
     */
    public CalculateFeeForProfitResponse createCalculateFeeForProfitResponse() {
        return new CalculateFeeForProfitResponse();
    }

    /**
     * Create an instance of {@link CalculateFeeForProfit }
     * 
     */
    public CalculateFeeForProfit createCalculateFeeForProfit() {
        return new CalculateFeeForProfit();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateFeeForProfit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.fee.jaxws.web.rik.otus.ru/", name = "calculateFeeForProfit")
    public JAXBElement<CalculateFeeForProfit> createCalculateFeeForProfit(CalculateFeeForProfit value) {
        return new JAXBElement<CalculateFeeForProfit>(_CalculateFeeForProfit_QNAME, CalculateFeeForProfit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateFeeForProfitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.fee.jaxws.web.rik.otus.ru/", name = "calculateFeeForProfitResponse")
    public JAXBElement<CalculateFeeForProfitResponse> createCalculateFeeForProfitResponse(CalculateFeeForProfitResponse value) {
        return new JAXBElement<CalculateFeeForProfitResponse>(_CalculateFeeForProfitResponse_QNAME, CalculateFeeForProfitResponse.class, null, value);
    }

}
