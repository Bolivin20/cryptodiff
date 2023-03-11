package com.cryptodiff.controller;

import com.cryptodiff.entity.Crypto;
import com.cryptodiff.marketMaps.HuobiMarket;
import com.cryptodiff.repository.CryptoRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class MainController {

    private HuobiMarket huobiMarket;
    private CryptoRepo cryptoRepo;

    @Autowired
    public MainController(HuobiMarket huobiMarket, CryptoRepo cryptoRepo) {
        this.huobiMarket = huobiMarket;
        this.cryptoRepo = cryptoRepo;
    }

    @GetMapping("/huobi")
    public String getHuobi() {
        Gson gson = new Gson();
        String json = gson.toJson(huobiMarket.getMarketPrices());
        return json;
    }

    @GetMapping("/admin")
    public String admin() {
        createCryptoRecords();
        return "Initiated";
    }
    //ta operacja byłaby wykonana tylko raz na init danych w tabeli bazy danych, potem juz tylko by były updateowane rekordy
    private void createCryptoRecords() {
        HashMap<String, Double> huobiPrices = huobiMarket.getMarketPrices();
        Iterator<Map.Entry<String, Double>> it = huobiPrices.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            String symbol = entry.getKey();
            double price = entry.getValue();
            cryptoRepo.save(new Crypto(symbol, price));
        }
    }

    @GetMapping("/update")
    public String update() {
        //docelowo update dla wszystkich giełd
        updateCryptoRecordsForHuobi();
        Gson gson = new Gson();
        return gson.toJson(cryptoRepo.findAll());
    }

    //TODO zrobic 2 getMappingi które zwraca najlepsza cene i gielde dla kupna i sprzedazy (chyba ze optymaniej bedzie jak front to wykona)

    private void updateCryptoRecordsForHuobi() {
        HashMap<String, Double> huobiPrices = huobiMarket.getMarketPrices();
        Iterator<Map.Entry<String, Double>> it = huobiPrices.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            String symbol = entry.getKey();
            double price = entry.getValue();
            cryptoRepo.updateCrypto(symbol, price);
        }
    }
}
