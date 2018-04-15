package ru.otus.rik.web.jaxws.salary.service;

import ru.otus.rikapi.service.UserService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebService;

@WebService(serviceName = "RikSalaryWebService", name = "RikSalaryService")
public class RikSalaryServiceImpl implements RikSalaryService {
    //@EJB
    @Inject
    private UserService userService;

    @Override
    public double averageSalary() {
        return userService.getAverageSalary();
    }
}
