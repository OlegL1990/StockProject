package com.olegluzin.priceservice.service;

import com.olegluzin.priceservice.client.StockServiceClient;
import com.olegluzin.priceservice.dto.StocksDto;
import com.olegluzin.priceservice.dto.StocksWithPricesDto;
import com.olegluzin.priceservice.exception.StockAlreadyExistException;
import com.olegluzin.priceservice.model.FigiWithPrice;
import com.olegluzin.priceservice.model.Stock;
import com.olegluzin.priceservice.model.StockWithPrice;
import com.olegluzin.priceservice.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceService {
    private final StockServiceClient stockServiceClient;
    private final PriceRepository priceRepository;

    public FigiWithPrice addStock(FigiWithPrice figiWithPrice) {
        if (priceRepository.existsById(figiWithPrice.getFigi())) {
            throw new StockAlreadyExistException("Stock already exists.");
        }

        return priceRepository.save(figiWithPrice);
    }

    public StocksWithPricesDto getPrices(StocksDto stocksDto) {
        long start = System.currentTimeMillis();
        List<StockWithPrice> result = new ArrayList<>();
        List<Stock> searchStockPrices = stocksDto.getStocks();
        List<StockWithPrice> fromRedis = getFromRedis(searchStockPrices);
        List<String> foundFigiesInRedis = fromRedis.stream().map(s -> s.getFigi()).collect(Collectors.toList());
        result.addAll(fromRedis);

        List<Stock> notFoundInRepo = searchStockPrices.stream()
                .filter(s -> !foundFigiesInRedis.contains(s.getFigi()))
                .collect(Collectors.toList());

        if (!notFoundInRepo.isEmpty()) {
            List<StockWithPrice> fromApi = getPricesFromApi(notFoundInRepo);
            result.addAll(fromApi);
            saveToRedis(fromApi);
        }
        log.info("All time - {}", System.currentTimeMillis() - start);
        return new StocksWithPricesDto(result);
    }

    private List<StockWithPrice> getFromRedis(List<Stock> searchStockPrices) {
        long start = System.currentTimeMillis();
        List<StockWithPrice> stocksFromRedis = new ArrayList<>();
        List<String> figies = searchStockPrices.stream().map(s -> s.getFigi()).collect(Collectors.toList());
        List<FigiWithPrice> foundFigies = new ArrayList<>();
        priceRepository.findAllById(figies).forEach(f -> foundFigies.add(f));
        if (!foundFigies.isEmpty()) {
            Map<String, Double> figiWithPrice = foundFigies.stream()
                    .collect(Collectors.toMap(FigiWithPrice::getFigi, FigiWithPrice::getPrice));

            searchStockPrices.stream()
                    .filter(s -> figiWithPrice.containsKey(s.getFigi()))
                    .forEach(s -> stocksFromRedis.add(new StockWithPrice(s, figiWithPrice.get(s.getFigi()))));
        }
        log.info("Time for getting from Reddis - {}", System.currentTimeMillis() - start);
        return stocksFromRedis;
    }


    private void saveToRedis(List<StockWithPrice> stocks) {
        priceRepository.saveAll(stocks.stream()
                .map(s -> new FigiWithPrice(s.getFigi(), s.getPrice(), s.getCurrency()))
                .collect(Collectors.toList()));
    }

    private List<StockWithPrice> getPricesFromApi(List<Stock> notFoundInRepo) {
        long start = System.currentTimeMillis();
        StocksDto stocksDto = new StocksDto(notFoundInRepo);
        StocksWithPricesDto stocksWithPrices = stockServiceClient.getPrices(stocksDto);
        log.info("Time for getting from API - {}", System.currentTimeMillis() - start);
        return stocksWithPrices.getStocksWithPrices();
    }
}
