package org.spring.restapi.currencyexchanger;

import org.modelmapper.ModelMapper;
import org.spring.restapi.currencyexchanger.dto.CurrencyDTO;
import org.spring.restapi.currencyexchanger.models.Currency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencyExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangerApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
