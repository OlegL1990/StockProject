package com.olegluzin.userservice.service;

import com.olegluzin.userservice.dto.StocksDto;
import com.olegluzin.userservice.dto.StocksWithPricesDto;
import com.olegluzin.userservice.exception.UserNotFoundException;
import com.olegluzin.userservice.model.ExchangeRates;
import com.olegluzin.userservice.model.Instrument;
import com.olegluzin.userservice.model.StockWithPrice;
import com.olegluzin.userservice.model.User;
import com.olegluzin.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final UserRepository userRepository;
    private final StockService stockService;
    private final PriceService priceService;
    private final CurrencyService currencyService;


    public Double getTotalPortfolioValue(String id){
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found. Try another id"));
        ExchangeRates rates = currencyService.getRates();
        StocksDto stocksDto = stockService.getStocksByUser(id);
        log.error(stocksDto.getStocks().toString()+"StocksDto");
        StocksWithPricesDto stocksWithPricesDto = priceService.getPrices(stocksDto);
        Map<String, Integer> tickersWithQuantity= getTickersWithQuantity(user);
        log.error(stocksWithPricesDto.getStocksWithPrices().toString()+"StocksWithPrices");
        Map<String, Double> tickersWithPrice = getTickersWithPrice(stocksWithPricesDto);
        return stocksDto.getStocks().stream()
                .map(s -> tickersWithPrice.get(s.getTicker())
                        * tickersWithQuantity.get(s.getTicker())
                        * rates.getRates().get(s.getCurrency()))
                .mapToDouble(Double::doubleValue).sum();
    }



    private Map<String, Integer> getTickersWithQuantity(User user) {
        Map<String, Integer> map = user.getPortfolio().stream().collect(Collectors.toMap(Instrument::getTicker, Instrument::getQuantity));
        log.error(map.toString());
        return map;
    }

    private Map<String, Double> getTickersWithPrice(StocksWithPricesDto stocksWithPricesDto) {
        Map<String, Double> map = stocksWithPricesDto.getStocksWithPrices().stream().collect(Collectors.toMap(StockWithPrice::getTicker, StockWithPrice::getPrice));
        log.error(map.toString());
        return map;
    }
}
