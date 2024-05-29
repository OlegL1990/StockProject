package com.olegluzin.moexservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "corporateClient", url = "${moex.bonds.corporate.url}", configuration = FeignConfig.class)
public interface CorporateBondsClient {
    @GetMapping
    String getBondsFromMoex();
}
