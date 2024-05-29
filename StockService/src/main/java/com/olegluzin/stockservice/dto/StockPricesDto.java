package com.olegluzin.stockservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StockPricesDto {
    private List<StockPrice> prices;
}
