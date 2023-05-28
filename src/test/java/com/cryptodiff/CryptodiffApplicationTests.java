package com.cryptodiff;

import com.cryptodiff.marketMaps.*;
import com.cryptodiff.marketMaps.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
class CryptodiffApplicationTests {


    @Test
    void shouldReturnHashmapKeysWithoutUsdt() {
        BinanceMarket binanceMarket = new BinanceMarket();
        assertFalse(binanceMarket.getMarketPrices().keySet().stream().anyMatch(c -> c.contains("usdt")));
        HuobiMarket huobiMarket = new HuobiMarket();
        BitstampMarket bitstampMarket = new BitstampMarket();
        assertFalse(bitstampMarket.getMarketPrices().keySet().stream().anyMatch(c -> c.contains("USD")));
        assertFalse(huobiMarket.getMarketPrices().keySet().stream().anyMatch(c -> c.contains("usdt")));
    }

    @Test
    void checkIfCreateListOfPricesWork() {
        BinanceMarket binanceMarket = new BinanceMarket();
        HuobiMarket huobiMarket = new HuobiMarket();
        BitstampMarket bitstampMarket = new BitstampMarket();
        HashMap<String, BigDecimal> huobi = huobiMarket.getMarketPrices();
        HashMap<String, BigDecimal> binance = binanceMarket.getMarketPrices();
        HashMap<String, BigDecimal> bitstamp = bitstampMarket.getMarketPrices();
        List<Currency> currenciesToSet = new ArrayList<>();
        List<Currency> revertCurrenciesToSet = new ArrayList<>();
        List<HashMap<String, BigDecimal>> markets = new ArrayList<>();
        List<String> namesOfMarkets = new ArrayList<>();
        markets.add(binance);
        markets.add(huobi);
        markets.add(bitstamp);
        namesOfMarkets.add("binance");
        namesOfMarkets.add("huobi");
        namesOfMarkets.add("bitstamp");
        MarketsManager marketsManager = new MarketsManager();
        marketsManager.createListOfPrices(markets, currenciesToSet, revertCurrenciesToSet, namesOfMarkets);
        List<String> allSymbolsAsc = currenciesToSet.stream().map(c -> c.getSymbol()).collect(Collectors.toList());
        Set<String> allSymbolsSet = new HashSet<>(allSymbolsAsc);
        System.out.println(allSymbolsSet.size() + " " + allSymbolsAsc.size());
        assertTrue(allSymbolsSet.size() == allSymbolsAsc.size());
        List<String> allSymbolsDesc = currenciesToSet.stream().map(c -> c.getSymbol()).collect(Collectors.toList());
        Set<String> allSymbolsSetDesc = new HashSet<>(allSymbolsDesc);
        System.out.println(allSymbolsSetDesc.size() + " " + allSymbolsDesc.size());
        assertTrue(allSymbolsSetDesc.size() == allSymbolsDesc.size());


    }
}
