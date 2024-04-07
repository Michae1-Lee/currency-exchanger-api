package org.spring.restapi.currencyexchanger.services;

import org.spring.restapi.currencyexchanger.CurrencyExchangerApplication;
import org.spring.restapi.currencyexchanger.models.Currency;
import org.spring.restapi.currencyexchanger.repositories.CurrencyRepository;
import org.spring.restapi.currencyexchanger.util.CurrencyExistsException;
import org.spring.restapi.currencyexchanger.util.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
    public List<Currency> findAll(){
        return currencyRepository.findAll();
    }
    public Currency find(String code){
        return currencyRepository.findByCode(code).orElseThrow(CurrencyNotFoundException::new);
    }
    public void save(Currency currency){
        try{
            find(currency.getCode());
            throw new CurrencyExistsException();
        }catch (CurrencyNotFoundException e){
            currencyRepository.save(currency);
        }
    }
}
