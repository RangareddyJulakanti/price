package com.price.demo.service;

import com.price.demo.domain.entity.EPrice;
import com.price.demo.domain.model.Price;
import com.price.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getAll() {
        Iterable<EPrice> list = priceRepository.findAll();
        List<Price> priceList = new ArrayList<>();
        list.forEach(e ->
                {
                    Price p = Price.builder()
                            .Id(e.getId())
                            .currency(e.getCurrency())
                            .value(e.getValue())
                            .build();
                    priceList.add(p);
                }

        );
        return priceList;
    }

    public Price getPriceById(Long priceId) {
        Optional<EPrice> priceOptional = priceRepository.findById(priceId);
        EPrice ePrice = priceOptional.get();
        return ePriceByPrice(ePrice);
    }


    public ResponseEntity savePrice(Price price) {
        EPrice ePrice=ePriceByPrice(price);
        priceRepository.save(ePrice);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/price/priceId/"+ePrice.getId())
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    public ResponseEntity update(Price price) {
        EPrice ePrice=ePriceByPrice(price);
       Optional<EPrice> ePriceOptional= priceRepository.findById(ePrice.getId());
       return ePriceOptional.map(e->{
          Price p= ePriceByPrice(e);
                   URI uri = ServletUriComponentsBuilder
                           .fromCurrentContextPath().path("/price/priceId/"+p.getId())
                           .buildAndExpand()
                           .toUri();
                   return ResponseEntity.created(uri).build();
       }
       ).orElseThrow(()->new EntityNotFoundException("entity not found for the givenId "+price.getId()));
    }
    private Price ePriceByPrice(EPrice e) {
        Price p = Price.builder()
                .Id(e.getId())
                .currency(e.getCurrency())
                .value(e.getValue())
                .build();
        return p;
    }

    private EPrice ePriceByPrice(Price e) {
        EPrice ePrice = new EPrice();
        ePrice.setId(e.getId());
        ePrice.setValue(e.getValue());
        ePrice.setCurrency(e.getCurrency());
        return ePrice;
    }


    public ResponseEntity deleteById(Long priceId) {
      Optional<EPrice> ePriceOptional=   priceRepository.findById(priceId);
        return ePriceOptional.map(e->{
                    Price p= ePriceByPrice(e);
                    URI uri = ServletUriComponentsBuilder
                            .fromCurrentContextPath().path("/price/priceId/"+p.getId())
                            .buildAndExpand()
                            .toUri();
                    priceRepository.deleteById(priceId);
                    return ResponseEntity.created(uri).build();
                }
        ).orElseThrow(()->new EntityNotFoundException("entity not found for the givenId "+priceId));
    }
}
