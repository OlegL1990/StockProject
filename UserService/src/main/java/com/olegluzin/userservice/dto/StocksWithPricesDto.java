package com.olegluzin.userservice.dto;

import com.olegluzin.userservice.model.StockWithPrice;
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
