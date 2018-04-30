package ru.otus.rik.service.creadit;

import ru.otus.rikapi.service.CreditService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public abstract class CreditServiceValidationDecorator implements CreditService {

    @Inject
    @Delegate
    @Any
    private CreditService delegate;

    @Override
    public double calculateAnnuityCreditPayment(int total, int period, double percent) {
        validate(total, period, percent);
        return delegate.calculateAnnuityCreditPayment(total, period, percent);
    }

    @Override
    public List<Double> calculateDifferentialCreditPayment(int total, int period, double percent) {
        validate(total, period, percent);
        return delegate.calculateDifferentialCreditPayment(total, period, percent);
    }

    private void validate(int total, int period, double percent) {
        if (percent > 50) {
            throw new RuntimeException("Too big percent, you are greedy");
        }
        if (total < 100) {
            throw new RuntimeException("Too low credit amount, we are proud bank");
        }
        if (period <= 0) {
            throw new RuntimeException("Period couldn't be negative, go to deposits");
        }
    }
}
