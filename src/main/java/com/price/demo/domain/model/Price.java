package com.price.demo.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Price implements Serializable {
    private Long Id;
    private String currency;
    private Long value;
}
