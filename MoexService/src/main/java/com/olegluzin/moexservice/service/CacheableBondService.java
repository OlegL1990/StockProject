package com.olegluzin.moexservice.service;

import com.olegluzin.moexservice.client.CorporateBondsClient;
import com.olegluzin.moexservice.client.GovernmentBondsClient;
import com.olegluzin.moexservice.dto.BondDto;
import com.olegluzin.moexservice.parser.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheableBondService {
    private final CorporateBondsClient corporateBondsClient;
    private final GovernmentBondsClient governmentBondsClient;
    private final Parser parser;
    @Cacheable(value = "corpBonds")
    public List<BondDto> getCorporateBonds(){
        log.info("Getting corporate bonds from Moex");
        List<BondDto> bondDtos = parser.parse(corporateBondsClient.getBondsFromMoex());
        if(bondDtos.isEmpty()){
            log.error("Moex is not answering");
        }
        return bondDtos;
    }
    @Cacheable(value = "govBonds")
    public List<BondDto> getGovernmentBonds(){
        log.info("Getting government bonds from Moex");
        List<BondDto> bondDtos = parser.parse(governmentBondsClient.getBondsFromMoex());
        if(bondDtos.isEmpty()){
            log.error("Moex is not answering");
        }
        return bondDtos;
    }
}
