
package ru.otus.rik.web.jaxws.salary.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.otus.rik.web.jaxws.salary.client package. 
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

    private final static QName _AverageSalary_QNAME = new QName("http://service.salary.jaxws.web.rik.otus.ru/", "averageSalary");
    private final static QName _AverageSalaryResponse_QNAME = new QName("http://service.salary.jaxws.web.rik.otus.ru/", "averageSalaryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.otus.rik.web.jaxws.salary.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AverageSalary }
     * 
     */
    public AverageSalary createAverageSalary() {
        return new AverageSalary();
    }

    /**
     * Create an instance of {@link AverageSalaryResponse }
     * 
     */
    public AverageSalaryResponse createAverageSalaryResponse() {
        return new AverageSalaryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AverageSalary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salary.jaxws.web.rik.otus.ru/", name = "averageSalary")
    public JAXBElement<AverageSalary> createAverageSalary(AverageSalary value) {
        return new JAXBElement<AverageSalary>(_AverageSalary_QNAME, AverageSalary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AverageSalaryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salary.jaxws.web.rik.otus.ru/", name = "averageSalaryResponse")
    public JAXBElement<AverageSalaryResponse> createAverageSalaryResponse(AverageSalaryResponse value) {
        return new JAXBElement<AverageSalaryResponse>(_AverageSalaryResponse_QNAME, AverageSalaryResponse.class, null, value);
    }

}
