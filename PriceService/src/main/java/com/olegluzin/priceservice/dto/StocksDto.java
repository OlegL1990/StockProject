package com.olegluzin.priceservice.dto;



import com.olegluzin.priceservice.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StocksDto {
    private List<Stock> stocks;
}
