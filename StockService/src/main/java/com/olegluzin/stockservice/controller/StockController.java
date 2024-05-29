package com.olegluzin.stockservice.controller;

import com.olegluzin.stockservice.dto.*;
import com.olegluzin.stockservice.model.StockWithPrice;
import com.olegluzin.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping("/getStocksByTickers")
    public StocksDto getStocksByTickers(@RequestBody TickersDto tickersDto) {
        return stockService.getStocksByTickers(tickersDto);
    }

    @PostMapping("/prices")
    public StocksWithPricesDto getPrices(@RequestBody StocksDto stocksDto){
        return stockService.getPrices(stocksDto);
    }

}

