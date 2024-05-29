package com.olegluzin.moexservice.dto;

import lombok.Value;

@Value
public class StockPrice {
    private String figi;
    private Double price;
}
