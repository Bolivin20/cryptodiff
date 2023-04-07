package com.cryptodiff.controller;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

@RestController
@Service
public class HistoricalPriceController {

    @GetMapping("/historical/{crypto}")
    public String getHistoricalCrypto(@PathVariable("crypto") String crypto){

        HashMap<Integer, Double> huobiPrices = new HashMap<>();
        String HUOBI_API_URL = "https://api.huobi.com/market/history/kline?symbol="+crypto+"usdt&period=1day&size=30";

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
                double open = obj.getDouble("open");
                huobiPrices.put(i, open);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(huobiPrices);

        return json;
    }
}
