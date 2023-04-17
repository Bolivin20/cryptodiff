package com.cryptodiff.controller;

import com.cryptodiff.marketMaps.BitstampMarket;
import com.cryptodiff.marketMaps.HuobiMarket;
import com.cryptodiff.repository.CryptoRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private HuobiMarket huobiMarket;
    private BitstampMarket bitstampMarket;
    private CryptoRepo cryptoRepo;

    @Autowired
    public MainController(HuobiMarket huobiMarket, BitstampMarket bitstampMarket, CryptoRepo cryptoRepo) {
        this.huobiMarket = huobiMarket;
        this.bitstampMarket = bitstampMarket;
        this.cryptoRepo = cryptoRepo;
    }

    @GetMapping("/huobi")
    public String getHuobi() {
        Gson gson = new Gson();
        String json = gson.toJson(huobiMarket.getMarketPrices());
        return json;
    }

    @GetMapping("/bitstamp")
    public String getBitstamp() {
        Gson gson = new Gson();
        String json = gson.toJson(bitstampMarket.getMarketPrices());
        return json;
    }
}
