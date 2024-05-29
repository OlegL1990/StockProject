package com.olegluzin.priceservice.repository;

import com.olegluzin.priceservice.model.FigiWithPrice;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends KeyValueRepository<FigiWithPrice, String> {

}
