package com.olegluzin.priceservice.controller;

import com.olegluzin.priceservice.dto.StocksDto;
import com.olegluzin.priceservice.dto.StocksWithPricesDto;
import com.olegluzin.priceservice.model.FigiWithPrice;
import com.olegluzin.priceservice.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/prices")
    public StocksWithPricesDto getStocksWithPrices(@RequestBody StocksDto stocksDto) {
        return priceService.getPrices(stocksDto);
    }

    @PostMapping("/add")
    public FigiWithPrice addStock(@RequestBody FigiWithPrice figiWithPrice) {
        return priceService.addStock(figiWithPrice);
    }

}
