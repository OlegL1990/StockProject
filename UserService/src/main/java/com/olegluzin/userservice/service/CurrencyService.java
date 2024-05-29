package com.olegluzin.userservice.service;

import com.olegluzin.userservice.model.Currency;
import com.olegluzin.userservice.model.ExchangeRates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

//add currency exchange rates mircoservice
@Service
@RequiredArgsConstructor
public class CurrencyService {

    public ExchangeRates getRates(){
        java.util.Map<Currency, Double> map = new HashMap<>();
        map.put(Currency.USD,88.6102);
        map.put(Currency.EUR,97.0659);
        map.put(Currency.GBP,111.7109);
        map.put(Currency.HKD,11.3866);
        map.put(Currency.CHF,100.6934);
        map.put(Currency.JPY,0.598273);
        map.put(Currency.CNY,12.3685);
        map.put(Currency.TRY,3.06775);
        return new ExchangeRates(map);
    }
}
