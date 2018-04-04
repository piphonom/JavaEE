package ru.otus.rik.web.jaxws.salary.test;

import ru.otus.rik.web.jaxws.salary.client.RikSalaryService;
import ru.otus.rik.web.jaxws.salary.client.RikSalaryWebService;

public class SalaryTest {

    RikSalaryWebService webService = new RikSalaryWebService();
    public static void main(String[] args) {
        RikSalaryService service = new SalaryTest().webService.getRikSalaryServicePort();
        System.out.println("Average salary: " + service.averageSalary());
    }
}
