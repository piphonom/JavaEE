package ru.otus.rik.web.gwt.shared.model;

public class CurrencyDTO {
    private String usd;
    private String eur;
    private String chf;

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public void setEur(String eur) {
        this.eur = eur;
    }

    public void setChf(String chf) {
        this.chf = chf;
    }

    public String getUsd() {
        return usd;
    }

    public String getEur() {
        return eur;
    }

    public String getChf() {
        return chf;
    }
}
