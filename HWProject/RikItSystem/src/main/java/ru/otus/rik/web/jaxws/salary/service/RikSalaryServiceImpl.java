package ru.otus.rik.web.jaxws.salary.service;

import ru.otus.rik.service.helpers.PersistenceServiceHolder;

import javax.jws.WebService;

@WebService(serviceName = "RikSalaryWebService", name = "RikSalaryService")
public class RikSalaryServiceImpl implements RikSalaryService {
    @Override
    public double averageSalary() {
        return PersistenceServiceHolder.getPersistenceService().getAverageSalary();
    }
}
