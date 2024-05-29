package com.olegluzin.userservice.client;

import com.olegluzin.userservice.dto.StocksDto;
import com.olegluzin.userservice.dto.StocksWithPricesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "priceclient",url = "http://priceservice:8004" )
public interface PriceClient {
    @PostMapping("/prices")
    StocksWithPricesDto getStocksWithPrices(@RequestBody StocksDto stocksDto);
}
