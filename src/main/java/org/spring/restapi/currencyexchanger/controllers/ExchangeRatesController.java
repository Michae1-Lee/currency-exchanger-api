package org.spring.restapi.currencyexchanger.controllers;

import org.spring.restapi.currencyexchanger.dto.CurrencyDTO;
import org.spring.restapi.currencyexchanger.dto.ExchangeRateDTO;
import org.spring.restapi.currencyexchanger.models.Currency;
import org.spring.restapi.currencyexchanger.models.ExchangeRate;
import org.spring.restapi.currencyexchanger.repositories.ExchangeRatesRepository;
import org.spring.restapi.currencyexchanger.services.CurrencyService;
import org.spring.restapi.currencyexchanger.services.ExchangeRatesService;
import org.spring.restapi.currencyexchanger.util.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange_rates")
public class ExchangeRatesController {
    private final ExchangeRatesService exchangeRatesService;
    private final CurrencyService currencyService;
    @Autowired
    public ExchangeRatesController(ExchangeRatesService exchangeRatesService, CurrencyService currencyService) {
        this.exchangeRatesService = exchangeRatesService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<ExchangeRate> getExchangeRates(){
       return exchangeRatesService.findAll();
    }
    @GetMapping("/{codes}")//USDRUB
    public ExchangeRate getExchangeRate(@PathVariable("codes") String codes){
        StringBuilder code1 = new StringBuilder();
        StringBuilder code2 = new StringBuilder();
        int i = 0;
        while(i<3){
            code1.append(codes.charAt(i));
            i++;
        }
        while(i<6){
            code2.append(codes.charAt(i));
            i++;
        }
        return exchangeRatesService.findByCodes(code1.toString(),code2.toString());
    }
    @PostMapping
    public ExchangeRate create(@RequestBody ExchangeRateDTO exchangeRateDTO){
        ExchangeRate exchangeRate = new ExchangeRate();
        ExchangeRate reverseExchangeRate = new ExchangeRate();

        Currency base_currency_id = currencyService.find(exchangeRateDTO.getBaseCurrencyCode());
        Currency target_currency_id = currencyService.find(exchangeRateDTO.getTargetCurrencyCode());
        double rate = exchangeRateDTO.getRate();

        reverseExchangeRate.setRate(1/rate);
        reverseExchangeRate.setBaseCurrency(target_currency_id);
        reverseExchangeRate.setTargetCurrency(base_currency_id);
        exchangeRatesService.save(reverseExchangeRate);

        exchangeRate.setRate(rate);
        exchangeRate.setBaseCurrency(base_currency_id);
        exchangeRate.setTargetCurrency(target_currency_id);
        exchangeRatesService.save(exchangeRate);

        return exchangeRatesService.find(exchangeRate.getId());
    }
}
