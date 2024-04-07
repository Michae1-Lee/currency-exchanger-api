package org.spring.restapi.currencyexchanger.repositories;

import org.spring.restapi.currencyexchanger.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    Optional<Currency> findByCode(String code);
}
