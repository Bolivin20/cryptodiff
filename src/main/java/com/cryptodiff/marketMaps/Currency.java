package com.cryptodiff.marketMaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.TreeMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency implements Comparable<Currency> {
    private String symbol;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal price;

    private TreeMap<String, BigDecimal> pricesMap = new TreeMap<>();


    public Currency() {
    }


    public Currency(String symbol, TreeMap<String, BigDecimal>  pricesMap) {
        this.symbol = symbol;
        this.pricesMap = pricesMap;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TreeMap<String, BigDecimal>  getPricesMap() {
        return pricesMap;
    }

    public void setPricesMap(TreeMap<String, BigDecimal>  pricesMap) {
        this.pricesMap = pricesMap;
    }

    @Override
    public int compareTo(Currency o) {
        int priceBool = this.pricesMap.firstEntry().getValue().compareTo(o.getPricesMap().firstEntry().getValue());
        int sizeBool = Integer.compare(this.pricesMap.size(), o.getPricesMap().size());
        if (priceBool + sizeBool > 0)
            return 1;
        else if (priceBool + sizeBool < 0)
            return -1;
        else if (priceBool == 0 && sizeBool == 0)
            return 0;
        else if (sizeBool >= 1)
            return 1;
        else if (sizeBool <= 1)
            return -1;
        else if (priceBool >= 1)
            return 1;
        else if (priceBool <= 1)
            return -1;
        else
            return 0;
    }

}
