package ru.otus.rik.service.creadit;

import java.util.List;

public interface CreditService {
    double calculateAnnuityCreditPayment(int total, int period, double percent);
    List<Double> calculateDifferentialCreditPayment(int total, int period, double percent);
}
