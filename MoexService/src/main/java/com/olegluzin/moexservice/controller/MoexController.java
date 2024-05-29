package com.olegluzin.moexservice.controller;

import com.olegluzin.moexservice.dto.FigiesDto;
import com.olegluzin.moexservice.dto.StockPricesDto;
import com.olegluzin.moexservice.dto.StocksDto;
import com.olegluzin.moexservice.dto.TickersDto;
import com.olegluzin.moexservice.service.BondService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bonds")
public class MoexController{
    private final BondService bondService;

    @PostMapping("/getBondsByTickers")
    public StocksDto getBondsFromMoex(@RequestBody TickersDto tickersDto){
        return bondService.getBondsByTickers(tickersDto);
    }

    @PostMapping("/prices")
    public StockPricesDto getPricesByFigies(@RequestBody FigiesDto figiesDto) {
        return bondService.getPricesByFigies(figiesDto);
    }
}
