package com.olegluzin.stockservice.model;

import lombok.Value;

@Value
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
}
