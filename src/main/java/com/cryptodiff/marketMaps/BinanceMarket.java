package com.cryptodiff.marketMaps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class BinanceMarket  extends MarketMaps{
    private final String BINANCE_URL = "https://www.binance.com/api/v3/ticker/price";
    @Autowired
    private RestTemplate restTemplate;

    public BinanceMarket(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BinanceMarket() {
        restTemplate = new RestTemplate();
    }


    @Override
    public HashMap<String, BigDecimal> getMarketPrices() {
        ResponseEntity<List<Currency>> response = restTemplate.exchange(BINANCE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Currency>>() {
        });
        HashMap<String, BigDecimal> currencies = (HashMap<String,BigDecimal>) response.getBody().stream().collect(Collectors.toMap(Currency::getSymbol, c -> c.getPrice()));

        return getOnlyUsdtPrices(currencies, "USDT");
    }


}
