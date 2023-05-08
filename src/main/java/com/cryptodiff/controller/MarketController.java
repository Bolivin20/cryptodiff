package com.cryptodiff.controller;

import com.cryptodiff.marketMaps.Currency;
import com.cryptodiff.marketMaps.MarketsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Component
public class MarketController {

    private final MarketsManager marketsManager;

    public MarketController(MarketsManager marketsManager) {
        this.marketsManager = marketsManager;
    }


    @GetMapping("/prices/desc")
    List<Currency> getAllMarketPricesDescending(){
        return  marketsManager.getCurrenciesWithRevertPricesMap();
    }

    @GetMapping("/prices/asc")
    List<Currency> getAllMarketPricesAscending(){
        return  marketsManager.getCurrencies();
    }

}
