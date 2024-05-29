package com.olegluzin.userservice.service;

import com.olegluzin.userservice.client.PriceClient;
import com.olegluzin.userservice.dto.StocksDto;
import com.olegluzin.userservice.dto.StocksWithPricesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PriceService {

    private final PriceClient priceClient;
    public StocksWithPricesDto getPrices(StocksDto stocksDto){
        return priceClient.getStocksWithPrices(stocksDto);
    }
}
