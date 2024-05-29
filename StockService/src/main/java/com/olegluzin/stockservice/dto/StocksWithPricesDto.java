package com.olegluzin.stockservice.dto;

import com.olegluzin.stockservice.model.StockWithPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StocksWithPricesDto {
     List<StockWithPrice> stocksWithPrices;
}
