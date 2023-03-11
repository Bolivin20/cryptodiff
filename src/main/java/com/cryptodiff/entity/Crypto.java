package com.cryptodiff.entity;

import javax.persistence.*;

@Entity
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "huobi", nullable = false)
    private Double huobi;

    //do stworzenia pola z pozostałymi giełdami

    //do stworzenia pole data, ktore bedzie updateowane tylko przy udanej zmianie cen-czyli transakcja

    public Crypto() {
    }

    public Crypto(String symbol, Double huobi) {
        this.symbol = symbol;
        this.huobi = huobi;
    }

    public String getSymbol() {
        return symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHuobi() {
        return huobi;
    }

    public void setHuobi(Double huobi) {
        this.huobi = huobi;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
