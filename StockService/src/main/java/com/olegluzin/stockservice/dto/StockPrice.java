package com.olegluzin.stockservice.dto;

import lombok.Value;

@Value
public class StockPrice {
    private String figi;
    private Double price;
}
