package com.price.demo.repository;

import com.price.demo.domain.entity.EPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<EPrice,Long> {
}
