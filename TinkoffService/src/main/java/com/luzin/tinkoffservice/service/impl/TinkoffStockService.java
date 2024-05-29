package com.luzin.tinkoffservice.service.impl;

import com.luzin.tinkoffservice.dto.*;
import com.luzin.tinkoffservice.model.Currency;
import com.luzin.tinkoffservice.model.Stock;
import com.luzin.tinkoffservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.InstrumentShort;
import ru.tinkoff.piapi.contract.v1.LastPrice;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import static ru.tinkoff.piapi.core.utils.MapperUtils.quotationToBigDecimal;

@Service
@RequiredArgsConstructor
public class TinkoffStockService implements StockService {

    private final InvestApi api;
    private final AsyncTinkoffStockService asyncTinkoffStockService;


    @Override
    public Stock getStockByTicker(String ticker) {
        var cf = api.getInstrumentsService().findInstrument(ticker);
        var item = cf.join().get(0);
        return new Stock(item.getTicker(),item.getFigi(),item.getName(),item.getInstrumentType(),Currency.RUB,"Tinkoff");
    }

    @Override
    public StockPricesDto getPricesByFigies(FigiesDto figiesDto) {
        List<CompletableFuture<List<LastPrice>>> list = new ArrayList<>();
        figiesDto.getFigies().forEach(figi -> list.add(asyncTinkoffStockService.getLastPrice(figi)));
        List<StockPrice> prices =  list.stream()
                .map(CompletableFuture::join)
                .map(item -> new StockPrice(
                        item.get(0).getFigi(),
                        (quotationToBigDecimal(item.get(0).getPrice())).doubleValue())).collect(Collectors.toList());
        return new StockPricesDto(prices);
    }


    @Override
    public StocksDto getStocksByTickers(TickersDto tickers) {
        List<CompletableFuture<List<InstrumentShort>>> list = new ArrayList<>();
        tickers.getTickers().forEach(ticker->list.add(asyncTinkoffStockService.getInstruments(ticker)));
        List<Stock> stocks =  list.stream()
                .map(CompletableFuture::join)
                .map(is->is.get(0))
                .filter(item-> Objects.nonNull(item))
                .map(item->new Stock(
                        item.getTicker(),
                        item.getFigi(),
                        item.getName(),
                        item.getInstrumentType(),
                        Currency.RUB,
                        "Tinkoff"))
                .collect(Collectors.toList());
        return new StocksDto(stocks);
    }
}
