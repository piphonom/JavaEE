package ru.otus.rik.service.creadit;

import ru.otus.rik.service.interceptors.InvocationTimeProfile;
import ru.otus.rikapi.service.CreditService;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

//@Remote(CreditService.class)
//@LocalBean
//@Stateless
@RequestScoped
@InvocationTimeProfile
public class CreditServiceImpl implements CreditService {
    @Override
    public double calculateAnnuityCreditPayment(int total, int period, double percent) {
        percent /= 1200;
        double payment = (total * percent) / (1 - (1 / Math.pow(1 + percent, period)));
        return payment;
    }

    @Override
    public List<Double> calculateDifferentialCreditPayment(int total, int period, double percent) {
        percent /= 1200;
        List<Double> payments = new ArrayList<>();
        double payed = 0;
        int i = 1;
        while (i <= period) {
            double payment = total/period + (total * (period - i++ + 1) * percent / period);
            payments.add(payment);
            payed += payment;
        }
        return payments;
    }
}
