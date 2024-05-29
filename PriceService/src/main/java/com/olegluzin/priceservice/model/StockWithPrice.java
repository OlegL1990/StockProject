package com.olegluzin.priceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockWithPrice {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
    Double price;


    public StockWithPrice(Stock stock,Double price) {
        this.ticker = stock.getTicker();
        this.figi = stock.getFigi();
        this.name = stock.getName();
        this.type = stock.getType();
        this.currency = stock.getCurrency();
        this.source = stock.getSource();
        this.price = price;
    }
}