package com.olegluzin.userservice.service;

import com.olegluzin.userservice.client.StockClient;
import com.olegluzin.userservice.dto.StocksDto;
import com.olegluzin.userservice.dto.TickersDto;
import com.olegluzin.userservice.exception.UserNotFoundException;
import com.olegluzin.userservice.model.Instrument;
import com.olegluzin.userservice.model.User;
import com.olegluzin.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockClient stockClient;
    private final UserRepository userRepository;

    public StocksDto getStocksByTickers(TickersDto tickersDto){
        return stockClient.getStocksByTickers(tickersDto);
    }

    public StocksDto getStocksByUser(String id){
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found. Try another id"));
        List<String> tickers = new ArrayList<>();
        for(Instrument instrument : user.getPortfolio()){
            tickers.add(instrument.getTicker());
        }
        log.error(tickers.toString()+"tickers");
        TickersDto tickersDto = new TickersDto(tickers);
        log.error(tickersDto.getTickers().toString()+"tickersDto");
        StocksDto stocksDto = getStocksByTickers(tickersDto);
        log.error(getStocksByTickers(tickersDto).getStocks().toString()+"method");;

        return stocksDto;
    }
}
