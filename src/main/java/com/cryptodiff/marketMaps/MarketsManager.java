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
                Map<String, Double> prices = new HashMap<>();
                Map<String, Double> revertPrices = new HashMap<>();
                prices.put(namesOfMarkets.get(i), markets.get(i).get(key));
                revertPrices.put(namesOfMarkets.get(i), markets.get(i).get(key));
                for (int j = i + 1; j < markets.size(); j++) {
                    if (markets.get(j).containsKey(key)) {
                        prices.put(namesOfMarkets.get(j), markets.get(j).get(key));
                        revertPrices.put(namesOfMarkets.get(j), markets.get(j).get(key));
                    }
                }

                Currency currency = new Currency(key, getSortedMapAsc(prices));
                Currency revertCurrency = new Currency(key, getSortedMapDesc(revertPrices));
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

    public TreeMap<String, Double> getSortedMapDesc(Map<String, Double> map) {
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String k1, String k2) {
                return map.get(k2).compareTo(map.get(k1));
            }
        };
        return (getSortedMap(map, comparator));
    }

    public TreeMap<String, Double> getSortedMapAsc(Map<String, Double> map) {
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String k1, String k2) {
                int option = map.get(k2).compareTo(map.get(k1));
                if (option == 0) return 0;
                else return -option;

            }
        };
        return (getSortedMap(map, comparator));
    }

    public TreeMap<String, Double> getSortedMap(Map<String, Double> map, Comparator comparator) {

        TreeMap<String, Double> sorted = new TreeMap<String, Double>(comparator);
        sorted.putAll(map);

        return sorted;
    }
}
