package org.spring.restapi.currencyexchanger.services;

import org.spring.restapi.currencyexchanger.models.ExchangeRate;
import org.spring.restapi.currencyexchanger.repositories.ExchangeRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExchangeRatesService {
    private final ExchangeRatesRepository exchangeRatesRepository;
    private final CurrencyService currencyService;
    @Autowired
    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository, CurrencyService currencyService) {
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.currencyService = currencyService;
    }
    public List<ExchangeRate> findAll(){
        return exchangeRatesRepository.findAll();
    }
    public ExchangeRate findByCodes(String code1, String code2){
        return exchangeRatesRepository.findByBaseCurrencyIdAndTargetCurrencyId
                (currencyService.find(code1), currencyService.find(code2)).orElse(null);
    }
    public ExchangeRate find(int id){
        return exchangeRatesRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(ExchangeRate exchangeRate){
        exchangeRatesRepository.save(exchangeRate);
    }
}
