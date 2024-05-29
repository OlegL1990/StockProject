package com.olegluzin.moexservice.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
}
