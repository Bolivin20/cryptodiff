package com.cryptodiff.marketMaps;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public interface MarketMaps {

    HashMap<String, Double> getMarketPrices();
    //metoda ktora zwraca tylko ceny krypto w dolarach
    void getOnlyUsdtPrices(HashMap<String, Double> marketPrices);
}
