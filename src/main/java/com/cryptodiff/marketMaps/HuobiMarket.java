package com.cryptodiff.marketMaps;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class HuobiMarket extends MarketMaps {

    private static final String HUOBI_API_URL = "https://api.huobi.pro/market/tickers";

    @Override
    public HashMap<String, Double> getMarketPrices() {
        HashMap<String, Double> huobiPrices = new HashMap<>();
        try {
            URL url = new URL(HUOBI_API_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray data = jsonObject.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                String symbol = obj.getString("symbol");
                double open = obj.getDouble("open");
                huobiPrices.put(symbol, open);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getOnlyUsdtPrices(huobiPrices, "usdt");
    }

//    @Override
//    public void getOnlyUsdtPrices(HashMap<String, Double> marketPrices) {
//        Iterator<Map.Entry<String, Double>> it = marketPrices.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Double> entry = it.next();
//            if (!entry.getKey().endsWith("usdt")) {
//                it.remove();
//            }
//        }
//    }


}
