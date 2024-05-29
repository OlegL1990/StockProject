package com.olegluzin.priceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@RedisHash(value = "Stock")
@Data
public class FigiWithPrice {
    @Id
    private String figi;
    private Double price;
    private Currency currency;
}
