package com.olegluzin.priceservice.dto;

import com.olegluzin.priceservice.model.StockWithPrice;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StocksWithPricesDto {
    List<StockWithPrice> stocksWithPrices;
}
