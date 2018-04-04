package ru.otus.rik.web.jaxws.salary.service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface RikSalaryService {
    @WebMethod
    @WebResult
    double averageSalary();
}
