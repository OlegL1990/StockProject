package com.luzin.tinkoffservice.service.impl;

import com.luzin.tinkoffservice.dto.FigiesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.InstrumentShort;
import ru.tinkoff.piapi.contract.v1.LastPrice;
import ru.tinkoff.piapi.core.InvestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Async
@Service
@RequiredArgsConstructor
public class AsyncTinkoffStockService {
    private final InvestApi api;
    public CompletableFuture<List<InstrumentShort>> getInstruments(String ticker){
        return api.getInstrumentsService().findInstrument(ticker);
    }
    public CompletableFuture<List<LastPrice>> getLastPrice(String figi){
        List<String> lfigi = new ArrayList<>();
        lfigi.add(figi);
       return api.getMarketDataService().getLastPrices(lfigi);
    }
}
