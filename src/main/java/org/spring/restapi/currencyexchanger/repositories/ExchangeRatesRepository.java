package org.spring.restapi.currencyexchanger.repositories;

import org.spring.restapi.currencyexchanger.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findByBaseCurrencyIdAndTargetCurrencyId(int baseCurrency_id, int targetCurrency_id);
}
