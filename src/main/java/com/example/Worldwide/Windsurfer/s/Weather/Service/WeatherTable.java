package com.example.Worldwide.Windsurfer.s.Weather.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;

public class WeatherTable implements ServiceWeatherTable {
    private String location;
    private float temp;
    private float wind_speed;
    private String lat;
    private String lon;
    String[] loc = {"Jastarnia", "Bridgetown", "Fortaleza", "Pissouri", "Le Morne"};


    @Override
    public HashMap getDataFromApi() throws IOException {
        HashMap<String, HashMap<String, WeatherTable>> locations = new HashMap<>();
        for(String i: loc) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weatherbit.io/v2.0/forecast/daily?city="+i+"&key=bba96cd3cf21434986430d7bb541bbfa")
                    .get()
                    .build();

            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject json = new JSONObject(responseBody.string());
            String lat = json.getString("lat");
            String lon = json.getString("lon");
            HashMap<String, WeatherTable> map = new HashMap<>();
            for (int j = 0; j < json.getJSONArray("data").length(); j++) {
                String date = json.getJSONArray("data").getJSONObject(j).get("valid_date").toString();
                float wind = Float.parseFloat(json.getJSONArray("data").getJSONObject(j).get("wind_spd").toString());
                float temp = Float.parseFloat(json.getJSONArray("data").getJSONObject(j).get("temp").toString());

                WeatherTable weatherTable = new WeatherTable(i,temp,wind,lat,lon);

                map.put(date,weatherTable);

            }
            locations.put(i, map);
        }
        return locations;
    }

    public WeatherTable(String location, float temp, float wind_speed, String lat, String lon) {
        this.location = location;
        this.temp = temp;
        this.wind_speed = wind_speed;
        this.lat = lat;
        this.lon = lon;
    }

    public WeatherTable() {
    }

    public float getTemp() {
        return temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

}
