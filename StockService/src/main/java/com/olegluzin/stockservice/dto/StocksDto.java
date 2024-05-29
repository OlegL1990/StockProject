package com.olegluzin.stockservice.dto;


import com.olegluzin.stockservice.model.Stock;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StocksDto {
    private List<Stock> stocks;
}
