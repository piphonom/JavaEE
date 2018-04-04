package ru.otus.rik.web.jaxws.salary.service;

import ru.otus.rik.service.user.UserService;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(serviceName = "RikSalaryWebService", name = "RikSalaryService")
public class RikSalaryServiceImpl implements RikSalaryService {
    @EJB
    private UserService userService;

    @Override
    public double averageSalary() {
        return userService.getAverageSalary();
    }
}
