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
public class BitstampMarket implements MarketMaps{

    private static final String BITSTAMP_API_URL = "https://www.bitstamp.net/api/v2/ticker/";

    @Override
    public HashMap<String, Double> getMarketPrices() {
        HashMap<String, Double> bitstampPrices = new HashMap<>();

        try {
            URL url = new URL(BITSTAMP_API_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray data = new JSONArray(response.toString());


            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                String pair = obj.getString("pair");
                double open = obj.getDouble("open");
                bitstampPrices.put(pair, open);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        getOnlyUsdtPrices(bitstampPrices);
        return bitstampPrices;
    }

    @Override
    public void getOnlyUsdtPrices(HashMap<String, Double> marketPrices) {
        Iterator<Map.Entry<String, Double>> it = marketPrices.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            if (!entry.getKey().endsWith("USD")) {
                it.remove();
            }
        }
    }
}
