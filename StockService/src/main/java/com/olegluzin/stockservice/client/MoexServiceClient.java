package com.olegluzin.stockservice.client;

import com.olegluzin.stockservice.dto.FigiesDto;
import com.olegluzin.stockservice.dto.StocksDto;
import com.olegluzin.stockservice.dto.StockPricesDto;
import com.olegluzin.stockservice.dto.TickersDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "moexservice", url = "http://moexservice:8002",configuration = FeignConfig.class)
public interface MoexServiceClient extends ApiStockService{
    @PostMapping("/bonds/getBondsByTickers")
    StocksDto getStocksByTickers(TickersDto tickersDto);

    @PostMapping("/bonds/prices")
    StockPricesDto getPrices(FigiesDto figiesDto);
}
