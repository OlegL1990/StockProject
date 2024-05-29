package com.olegluzin.stockservice.client;

import com.olegluzin.stockservice.dto.FigiesDto;
import com.olegluzin.stockservice.dto.StockPricesDto;
import com.olegluzin.stockservice.dto.StocksDto;
import com.olegluzin.stockservice.dto.TickersDto;

public interface ApiStockService {
        StockPricesDto getPrices(FigiesDto figiesDto);
        StocksDto getStocksByTickers(TickersDto tickersDto);
    }
