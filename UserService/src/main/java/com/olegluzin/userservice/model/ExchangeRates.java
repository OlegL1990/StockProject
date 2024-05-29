package com.olegluzin.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
@Data
@AllArgsConstructor
public class ExchangeRates {
    private Map<Currency, Double> rates;
}
