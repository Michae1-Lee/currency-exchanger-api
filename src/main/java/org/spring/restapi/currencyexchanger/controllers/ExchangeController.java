package org.spring.restapi.currencyexchanger.controllers;

import org.spring.restapi.currencyexchanger.models.ExchangeRate;
import org.spring.restapi.currencyexchanger.models.Exchanged;
import org.spring.restapi.currencyexchanger.services.CurrencyService;
import org.spring.restapi.currencyexchanger.services.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeRatesService exchangeRatesService;
    private final CurrencyService currencyService;
    @Autowired
    public ExchangeController(ExchangeRatesService exchangeRatesService, CurrencyService currencyService) {
        this.exchangeRatesService = exchangeRatesService;
        this.currencyService = currencyService;
    }
    @GetMapping()
    public Exchanged exchanger(@RequestParam String from, @RequestParam String to,
                               @RequestParam double amount){

        Exchanged exchanged = new Exchanged();
        ExchangeRate exchangeRate = exchangeRatesService.findByCodes(from,to);

        exchanged.setBaseCurrency(currencyService.find(from));
        exchanged.setTargetCurrency(currencyService.find(to));

        exchanged.setRate(exchangeRate.getRate());
        exchanged.setAmount(amount);
        exchanged.setConvertedAmount(exchangeRate.getRate()*amount);

        return exchanged;
    }
}
