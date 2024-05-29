package com.olegluzin.moexservice.service;

import com.olegluzin.moexservice.client.CorporateBondsClient;
import com.olegluzin.moexservice.client.GovernmentBondsClient;
import com.olegluzin.moexservice.dto.*;
import com.olegluzin.moexservice.model.Currency;
import com.olegluzin.moexservice.model.Stock;
import com.olegluzin.moexservice.parser.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BondService {

    private final CacheableBondService cacheableBondService;
    public StocksDto getBondsByTickers(TickersDto tickersDto){
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(cacheableBondService.getCorporateBonds());
        allBonds.addAll(cacheableBondService.getGovernmentBonds());
        List<BondDto> resultBonds = allBonds.stream()
                .filter(bondDto ->tickersDto.getTickers().contains(bondDto.getTicker()))
                .collect(Collectors.toList());
        List<Stock> stocks = resultBonds.stream()
                .map(bondDto -> {
                    return Stock.builder()
                            .ticker(bondDto.getTicker())
                            .name(bondDto.getName())
                            .figi(bondDto.getTicker())
                            .type("Bond")
                            .currency(Currency.RUB)
                            .source("Moex")
                            .build();
                })
                .collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    public StockPricesDto getPricesByFigies(FigiesDto figiesDto){
        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(cacheableBondService.getCorporateBonds());
        allBonds.addAll(cacheableBondService.getGovernmentBonds());
        List<StockPrice> prices = allBonds.stream()
                .filter(bondDto -> figiesDto.getFigies().contains(bondDto.getTicker()))
                .map(bondDto -> new StockPrice(bondDto.getTicker(),bondDto.getPrice()*10))
                .collect(Collectors.toList());
        return new StockPricesDto(prices);

    }

}
