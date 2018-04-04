package ru.otus.rik.web.jaxws.fee.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface FeeWebService {
    @WebMethod
    @WebResult
    double calculateFeeForProfit(
            @WebParam double profit,
            @WebParam double consumption,
            @WebParam double feeCoefficient);
}
