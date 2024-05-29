package com.olegluzin.moexservice.dto;

import lombok.Value;

import java.util.List;

@Value
public class StockPricesDto {
    private List<StockPrice> prices;
}
