package com.example.Worldwide.Windsurfer.s.Weather.Service;

import lombok.SneakyThrows;
import java.util.HashMap;


public class RunThread implements Runnable, ServiceRunThread {

    public static HashMap<String, HashMap<String, WeatherTable>> location = new HashMap<>();
    ServiceWeatherTable serviceWeatherTable;


    public RunThread(ServiceWeatherTable serviceWeatherTable) {
        this.serviceWeatherTable = serviceWeatherTable;
    }

    @SneakyThrows
    @Override
    public void run() {
        location = serviceWeatherTable.getDataFromApi();
    }
    public static HashMap<String, HashMap<String, WeatherTable>> getLocations() {
        return location;
    }

    public void setLocations(HashMap<String, HashMap<String, WeatherTable>> locations) {
        this.location = locations;
    }
}
