package ru.otus.rik.web.jaxws.fee.service;

import javax.jws.WebService;

@WebService(name = "FeeWebService")
public class FeeWebServiceImpl implements FeeWebService {
    @Override
    public double calculateFeeForProfit(double profit, double consumption, double feeCoefficient) {
        return (profit - consumption) * feeCoefficient / 100;
    }
}
