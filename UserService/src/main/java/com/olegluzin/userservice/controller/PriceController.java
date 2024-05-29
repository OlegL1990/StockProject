package com.olegluzin.userservice.controller;

import com.olegluzin.userservice.dto.StocksDto;
import com.olegluzin.userservice.dto.StocksWithPricesDto;
import com.olegluzin.userservice.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;
    @PostMapping
    public StocksWithPricesDto getStocksWithPrices(@RequestBody StocksDto stocksDto) {
        return priceService.getPrices(stocksDto);
    }


}
