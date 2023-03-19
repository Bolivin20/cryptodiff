package com.cryptodiff.marketMaps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinanceMarket  implements MarketMaps{
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
    public HashMap<String, Double> getMarketPrices() {
        ResponseEntity<List<Currency>> response = restTemplate.exchange(BINANCE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Currency>>() {
        });

        HashMap<String, Double> currencies = (HashMap<String, Double>) response.getBody().stream().collect(Collectors.toMap(Currency::getSymbol, Currency::getPrice));
//        for (Map.Entry<String, Double> entry : currencies.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }
        return currencies;
    }


    @Override
    public void getOnlyUsdtPrices(HashMap<String, Double> marketPrices) {

    }
}
