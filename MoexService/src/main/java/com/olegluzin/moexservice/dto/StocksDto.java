package com.olegluzin.moexservice.dto;

import com.olegluzin.moexservice.model.Stock;
import lombok.Value;

import java.util.List;

@Value
public class StocksDto {
    List<Stock> stocks;
}
