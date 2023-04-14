package com.cryptodiff.marketMaps;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableScheduling
public class MarketsManager {

    private List<Currency> currencies;
    private List<Currency> currenciesWithRevertPricesMap;
    private BinanceMarket binanceMarket;
    private HuobiMarket huobiMarket;
    private BitstampMarket bitstampMarket;

    public MarketsManager() {
        bitstampMarket = new BitstampMarket();
        huobiMarket = new HuobiMarket();
        binanceMarket = new BinanceMarket();
        currenciesWithRevertPricesMap = new ArrayList<>();
        currencies = new ArrayList<>();
    }

    public void createListOfPrices(List<HashMap<String, Double>> markets, List<Currency> currenciesToSet, List<Currency> revertCurrenciesToSet, List<String> namesOfMarkets) {
        for (int i = 0; i < markets.size(); i++) {
            for (String key : markets.get(i).keySet()) {
                TreeMap<Double, String> prices = new TreeMap<>();
                TreeMap<Double, String> revertPrices = new TreeMap<>(Comparator.reverseOrder());
                prices.put(markets.get(i).get(key), namesOfMarkets.get(i));
                revertPrices.put(markets.get(i).get(key), namesOfMarkets.get(i));
                for (int j = i + 1; j < markets.size(); j++) {
                    if (markets.get(j).containsKey(key)) {
                        prices.put(markets.get(j).get(key), namesOfMarkets.get(j));
                        revertPrices.put(markets.get(j).get(key), namesOfMarkets.get(j));
                    }
                }

                Currency currency = new Currency(key, prices);
                Currency revertCurrency = new Currency(key, revertPrices);
                currenciesToSet.add(currency);
                revertCurrenciesToSet.add(revertCurrency);
            }
        }
        Collections.sort(currenciesToSet, Comparator.reverseOrder());
        Collections.sort(revertCurrenciesToSet, Comparator.reverseOrder());
    }

    @Scheduled(fixedDelay = 900000, initialDelay = 1)
    public void getDataFromMarkets() {
        HashMap<String, Double> binance = binanceMarket.getMarketPrices();
        HashMap<String, Double> huobi = huobiMarket.getMarketPrices();
        HashMap<String, Double> bitstamp = bitstampMarket.getMarketPrices();
        List<Currency> currenciesToSet = new ArrayList<>();
        List<Currency> revertCurrenciesToSet = new ArrayList<>();
        List<HashMap<String, Double>> markets = new ArrayList<>();
        List<String> namesOfMarkets = new ArrayList<>();
        markets.add(binance);
        markets.add(huobi);
        markets.add(bitstamp);
        namesOfMarkets.add("binance");
        namesOfMarkets.add("huobi");
        namesOfMarkets.add("bitstamp");
        createListOfPrices(markets, currenciesToSet, revertCurrenciesToSet, namesOfMarkets);
        currencies = currenciesToSet;
        currenciesWithRevertPricesMap = revertCurrenciesToSet;

    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Currency> getCurrenciesWithRevertPricesMap() {
        return currenciesWithRevertPricesMap;
    }
}
