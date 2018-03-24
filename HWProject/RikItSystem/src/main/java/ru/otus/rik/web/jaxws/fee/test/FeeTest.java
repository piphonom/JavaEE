package ru.otus.rik.web.jaxws.fee.test;

import ru.otus.rik.web.jaxws.fee.client.FeeWebService;
import ru.otus.rik.web.jaxws.fee.client.FeeWebServiceImplService;

public class FeeTest {

    private FeeWebServiceImplService webService = new FeeWebServiceImplService();

    public static void main(String[] args) {
        FeeTest feeTest = new FeeTest();

        FeeWebService feeService = feeTest.webService.getFeeWebServicePort();
        double fee = feeService.calculateFeeForProfit(100000, 20000, 13);
        System.out.println("Fee is: " + fee);
    }
}

