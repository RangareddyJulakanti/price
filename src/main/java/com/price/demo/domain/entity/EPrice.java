package com.price.demo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRICE")
@Getter
@Setter
public class EPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long Id;

    @Column(name="CURRENCY")
    private String currency;

    @Column(name="VALUE")
    private Long value;


}
