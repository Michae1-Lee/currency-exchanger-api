package org.spring.restapi.currencyexchanger.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.spring.restapi.currencyexchanger.dto.CurrencyDTO;
import org.spring.restapi.currencyexchanger.models.Currency;
import org.spring.restapi.currencyexchanger.services.CurrencyService;
import org.spring.restapi.currencyexchanger.util.CurrencyExistsException;
import org.spring.restapi.currencyexchanger.util.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrenciesController {
    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;
    @Autowired
    public CurrenciesController(CurrencyService currencyService, ModelMapper modelMapper) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CurrencyDTO> allCurrencies(){
        return currencyService.findAll().stream().map(this::convertToCurrencyDTO).toList();
    }
    @GetMapping("{code}")
    public CurrencyDTO getCurrency(@PathVariable("code") String code){
        code = code.toUpperCase();
        return convertToCurrencyDTO(currencyService.find(code));
    }
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @Valid CurrencyDTO currencyDTO){
        currencyService.save(convertToCurrency(currencyDTO));
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(CurrencyExistsException e){
        return new ResponseEntity<>("Currency already exists", HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(CurrencyNotFoundException e){
        return new ResponseEntity<>("Currency not found", HttpStatus.NOT_FOUND);
    }
    public CurrencyDTO convertToCurrencyDTO(Currency currency){
        return modelMapper.map(currency, CurrencyDTO.class);
    }
    public Currency convertToCurrency(CurrencyDTO currencyDTO){
        return modelMapper.map(currencyDTO, Currency.class);
    }
}
