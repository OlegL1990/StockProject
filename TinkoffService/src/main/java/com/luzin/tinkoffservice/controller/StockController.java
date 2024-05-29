package com.luzin.tinkoffservice.controller;

import com.luzin.tinkoffservice.dto.FigiesDto;
import com.luzin.tinkoffservice.dto.StockPricesDto;
import com.luzin.tinkoffservice.dto.StocksDto;
import com.luzin.tinkoffservice.dto.TickersDto;
import com.luzin.tinkoffservice.model.Stock;
import com.luzin.tinkoffservice.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.piapi.core.InvestApi;


import java.util.concurrent.ExecutionException;
@Slf4j
@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping(("/stocks/{ticker}"))
    public  Stock getStock(@PathVariable String ticker){
        return stockService.getStockByTicker(ticker);
    }
    @PostMapping("/prices")
    public StockPricesDto getPricesStocksByFigies(@RequestBody FigiesDto figiesDto) {
        return stockService.getPricesByFigies(figiesDto);
    }

    @PostMapping("/stocks/getStocksByTickers")
    public StocksDto getStocksByTickers(@RequestBody TickersDto tickers) {
        return stockService.getStocksByTickers(tickers);
    }

    }
