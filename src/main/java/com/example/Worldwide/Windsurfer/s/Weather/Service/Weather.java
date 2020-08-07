package com.example.Worldwide.Windsurfer.s.Weather.Service;

public class Weather {

    private String location;
    private String temp;
    private String wind_speed;
    private String date;

    public Weather(String location, String temp, String wind_speed, String date) {
        this.location = location;
        this.temp = temp;
        this.wind_speed = wind_speed;
        this.date = date;
    }

    //public Weather(String location) {
        //this.location = location;
  //  }

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
