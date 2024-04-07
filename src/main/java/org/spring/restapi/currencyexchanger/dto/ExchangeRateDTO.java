package org.spring.restapi.currencyexchanger.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.spring.restapi.currencyexchanger.models.Currency;
import org.spring.restapi.currencyexchanger.models.ExchangeRate;

public class ExchangeRateDTO {
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private double rate;
    public ExchangeRateDTO(){

    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public void setBaseCurrencyCode(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
