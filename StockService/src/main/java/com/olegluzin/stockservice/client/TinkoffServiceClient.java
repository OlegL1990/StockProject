package com.olegluzin.stockservice.client;

import com.olegluzin.stockservice.dto.FigiesDto;
import com.olegluzin.stockservice.dto.StockPricesDto;
import com.olegluzin.stockservice.dto.StocksDto;
import com.olegluzin.stockservice.dto.TickersDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "tinkoffservice", url = "http://tinkoffservice:8000" ,configuration = FeignConfig.class)
public interface TinkoffServiceClient extends ApiStockService{
    @PostMapping("/stocks/getStocksByTickers")
    StocksDto getStocksByTickers(TickersDto tickersDto);


    @PostMapping("/prices")
    StockPricesDto getPrices(FigiesDto figiesDto);
}
