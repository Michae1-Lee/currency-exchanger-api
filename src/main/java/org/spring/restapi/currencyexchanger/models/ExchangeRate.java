package org.spring.restapi.currencyexchanger.models;

import jakarta.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "base_currency_id", referencedColumnName = "id")
    private Currency baseCurrency;
    @ManyToOne
    @JoinColumn(name = "target_currency_id", referencedColumnName = "id")
    private Currency targetCurrency;

    @Column(name = "rate")
    private double rate;
    public ExchangeRate(){

    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
