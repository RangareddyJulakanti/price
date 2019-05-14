package com.price.demo.controller;

import com.price.demo.domain.model.Price;
import com.price.demo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping("/all")
    public List<Price> getAll(){
     return    priceService.getAll();
    }

    @GetMapping("/priceId/{priceId}")
    public Price getPriceById(@PathVariable("priceId") Long priceId){
        return priceService.getPriceById(priceId);
    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Price price){
        return priceService.savePrice(price);
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Price price){
        return priceService.update(price);
    }
    @DeleteMapping("/delete/priceId/{priceId}")
    public ResponseEntity delete(@PathVariable("priceId") String priceId){
        return priceService.deleteById(Long.valueOf(priceId));
    }
}
