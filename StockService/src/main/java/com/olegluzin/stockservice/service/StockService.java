package com.olegluzin.stockservice.service;

import com.olegluzin.stockservice.client.ApiStockService;
import com.olegluzin.stockservice.client.MoexServiceClient;
import com.olegluzin.stockservice.client.TinkoffServiceClient;
import com.olegluzin.stockservice.dto.*;
import com.olegluzin.stockservice.exception.StockNotFoundException;
import com.olegluzin.stockservice.model.Stock;
import com.olegluzin.stockservice.model.StockWithPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    private final MoexServiceClient moexServiceClient;
    private final TinkoffServiceClient tinkoffServiceClient;

    public StocksDto getStocksByTickers(TickersDto tickersDto) {
        List<String> tickers = tickersDto.getTickers();
        List<Stock> resultList = new ArrayList<>();
        List<Stock> stocksFromTinkoff = tinkoffServiceClient.getStocksByTickers(tickersDto).getStocks();
        resultList.addAll(stocksFromTinkoff);
        List<String> tickersFromTinkoff = stocksFromTinkoff.stream().map(s -> s.getTicker()).collect(Collectors.toList());
        tickers.removeAll(tickersFromTinkoff);
        if(!tickers.isEmpty()){
            List<Stock> stocksFromMoex = moexServiceClient.getStocksByTickers(new TickersDto(tickers)).getStocks();
            List<String> tickerFromMoex = stocksFromMoex.stream().map(s -> s.getTicker()).collect(Collectors.toList());
            tickers.removeAll(tickerFromMoex);
            resultList.addAll(stocksFromMoex);
        }
        return new StocksDto(resultList);
    }

    public StocksWithPricesDto getPrices(StocksDto stocksDto) {
        List<StockWithPrice> result = new ArrayList<>();
        List<Stock> fromMoex = new ArrayList<>();
        List<Stock> fromTinkoff = new ArrayList<>();
        sortBySource(stocksDto.getStocks(),fromMoex,fromTinkoff );
        getPricesByService(moexServiceClient,stocksDto.getStocks(),result);
        getPricesByService(tinkoffServiceClient, fromTinkoff, result);
        return new StocksWithPricesDto(result);
    }

    private void sortBySource(List<Stock> stocks, List<Stock> fromMoex, List<Stock> fromTinkoff) {
        stocks.forEach(s -> {
            if (s.getSource().equals("Moex")) {
                fromMoex.add(s);
            } else if (s.getSource().equals("Tinkoff")) {
                fromTinkoff.add(s);
            } else {
                throw new StockNotFoundException(String.format("Source - %s not found", s.getSource()));
            }
        });
    }
    private void getPricesByService(ApiStockService stockService,List<Stock> stocks, List<StockWithPrice> result ){
        if(!stocks.isEmpty()) {
            StockPricesDto prices = stockService.getPrices(
                    new FigiesDto(stocks.stream().map(m -> m.getFigi()).collect(Collectors.toList())));
            Map<String, Double> pricesMap = prices.getPrices().stream()
                    .collect(Collectors.toMap(StockPrice::getFigi, StockPrice::getPrice));
            stocks.forEach(f -> result.add(new StockWithPrice(f,pricesMap.get(f.getFigi()))));
        }
}
}