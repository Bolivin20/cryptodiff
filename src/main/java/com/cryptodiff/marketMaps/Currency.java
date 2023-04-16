package com.cryptodiff.marketMaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.TreeMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency implements Comparable<Currency> {
    private String symbol;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double price;

    private TreeMap<String, Double> pricesMap = new TreeMap<>();


    public Currency() {
    }


    public Currency(String symbol, TreeMap<String, Double>  pricesMap) {
        this.symbol = symbol;
        this.pricesMap = pricesMap;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public TreeMap<String, Double>  getPricesMap() {
        return pricesMap;
    }

    public void setPricesMap(TreeMap<String, Double>  pricesMap) {
        this.pricesMap = pricesMap;
    }

    @Override
    public int compareTo(Currency o) {
        int priceBool = Double.compare(this.pricesMap.firstEntry().getValue(), o.getPricesMap().firstEntry().getValue());
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
