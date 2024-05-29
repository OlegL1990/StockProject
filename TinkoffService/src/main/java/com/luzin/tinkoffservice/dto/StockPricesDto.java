package com.luzin.tinkoffservice.dto;

import lombok.Value;

import java.util.List;

@Value
public class StockPricesDto {
    List<StockPrice> prices;
}
