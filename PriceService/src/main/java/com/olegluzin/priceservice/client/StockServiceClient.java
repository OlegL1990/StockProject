package com.olegluzin.priceservice.client;

import com.olegluzin.priceservice.dto.StocksDto;
import com.olegluzin.priceservice.dto.StocksWithPricesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "stockservice", url = "http://stockService:8001")
public interface StockServiceClient {
    @PostMapping("/stocks/prices")
    StocksWithPricesDto getPrices(StocksDto stocksDto);
}
