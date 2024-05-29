package com.luzin.tinkoffservice.service;

import com.luzin.tinkoffservice.dto.FigiesDto;
import com.luzin.tinkoffservice.dto.StockPricesDto;
import com.luzin.tinkoffservice.dto.StocksDto;
import com.luzin.tinkoffservice.dto.TickersDto;
import com.luzin.tinkoffservice.model.Stock;

public interface StockService {
    Stock getStockByTicker(String ticker);
    StockPricesDto getPricesByFigies(FigiesDto figiesDto);
    StocksDto getStocksByTickers(TickersDto tickers);
}
