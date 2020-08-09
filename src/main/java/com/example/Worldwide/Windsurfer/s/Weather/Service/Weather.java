package com.example.Worldwide.Windsurfer.s.Weather.Service;

public class Weather {

    private String location;
    private float temp;
    private float wind_speed;
    private String date;

    public Weather(String location, float temp, float wind_speed, String date) {
        this.location = location;
        this.temp = temp;
        this.wind_speed = wind_speed;
        this.date = date;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
