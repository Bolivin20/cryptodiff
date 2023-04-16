package com.cryptodiff.marketMaps;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public abstract class MarketMaps {


    abstract HashMap<String, BigDecimal> getMarketPrices();

    HashMap<String, BigDecimal> getOnlyUsdtPrices(HashMap<String, BigDecimal> marketPrices, String currency) {
        return marketPrices.keySet().stream().filter(k -> k.contains(currency)).collect(Collectors.toMap(k -> k.replace(currency, "").toLowerCase(), v -> marketPrices.get(v), (v1, v2) -> v1, HashMap::new));

    }
}
