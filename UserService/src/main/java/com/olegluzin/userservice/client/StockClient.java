package com.olegluzin.userservice.client;

import com.olegluzin.userservice.dto.StocksDto;
import com.olegluzin.userservice.dto.TickersDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stockclient",url = "http://stockService:8001")
public interface StockClient {
    @PostMapping("stocks/getStocksByTickers")
     StocksDto getStocksByTickers(@RequestBody TickersDto tickersDto);
}
