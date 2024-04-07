package org.spring.restapi.currencyexchanger.repositories;

import org.spring.restapi.currencyexchanger.models.Currency;
import org.spring.restapi.currencyexchanger.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findByBaseCurrencyIdAndTargetCurrencyId(Currency base_currency, Currency target_currency);
}
