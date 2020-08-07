package com.example.Worldwide.Windsurfer.s.Weather.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class WeatherTable {
    private String location;
    private String temp;
    private String wind_speed;
    private String lat;
    private String lon;
    static String[] loc = {"Jastarnia", "Bridgetown", "Fortaleza", "Pissouri", "Le Morne"};
    static HashMap<String, HashMap<String, WeatherTable>> locations = new HashMap<>();

    public static HashMap getDataFromApi() throws IOException {
        for(String i: loc) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weatherbit.io/v2.0/forecast/daily?city="+i+"&key=8182fc129a9045afbf64ed33dee7f3f9")
                    .get()
                    .build();

            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject json = new JSONObject(responseBody.string());
            String lat = json.getString("lat");
            String lon = json.getString("lon");
            HashMap<String, WeatherTable> map = new HashMap<>();
            for (int j = 0; j < json.getJSONArray("data").length(); j++) {
                String date = json.getJSONArray("data").getJSONObject(j).get("valid_date").toString();
                String wind = json.getJSONArray("data").getJSONObject(j).get("wind_spd").toString();
                String temp = json.getJSONArray("data").getJSONObject(j).get("temp").toString();

                WeatherTable weatherTable = new WeatherTable(i,temp,wind,lat,lon);

                map.put(date,weatherTable);

            }
            locations.put(i, map);
        }
        return locations;
    }

    public WeatherTable(String location, String temp, String wind_speed, String lat, String lon) {
        this.location = location;
        this.temp = temp;
        this.wind_speed = wind_speed;
        this.lat = lat;
        this.lon = lon;
    }

    public WeatherTable() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
