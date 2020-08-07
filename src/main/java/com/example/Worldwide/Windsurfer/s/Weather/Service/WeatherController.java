package com.example.Worldwide.Windsurfer.s.Weather.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    private SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
    private Date date_st = new Date(System.currentTimeMillis());
    private String date_str = formatter.format(date_st);
    HashMap<String, HashMap<String, WeatherTable>> location = new HashMap<>();
    Weather weather;

    @GetMapping("/weather")
    public Weather WeatherForm(
            @RequestParam(value = "date", required = false) String date
    ) {
        if (date == null) {
            date = date_str;
        }
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        boolean isDate1 = date.matches(datePattern);
        if (!isDate1) {
            throw new ErrorController.DateFormatException();
        }
        location = Application.getLocation();
        boolean flag = false;
        float max = 0;
        float result;
        for(Map.Entry<String, HashMap<String, WeatherTable>> entry: location.entrySet()) {
            float temp;
            float wind_spd;
            try{
                temp = Float.parseFloat(entry.getValue().get(date).getTemp());
                wind_spd = Float.parseFloat(entry.getValue().get(date).getWind_speed());
            } catch (Exception e) {
                throw new ErrorController.DateNotfoundException();
            }

            if(wind_spd >=5 || wind_spd <=18 || temp >=5 || temp <= 35) {
                flag = true;
            }
            result = wind_spd*3+temp;
            if (result > max && flag)
            {
                max = result;
                weather = new Weather(entry.getKey(),Float.toString(temp),Float.toString(wind_spd),date);
            }
        }
         return weather;
        //return location;
        //return new Weather(String.format(template, max_loc));
    }
}


/*//Weather[] locations = {new Weather("Jastarnia"),new Weather("Bridgetown"), new Weather("Fortaleza"), new Weather("Pissouri"),new Weather("Le Morne")};
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @lombok.SneakyThrows
            @Override
            public void run() {
                location = WeatherTable.getDataFromApi();
            }
        }, 0, 30, TimeUnit.SECONDS);
        */

/*
        float max = 0;
        Weather weather = null;
        for(Weather i:locations) {
            boolean flag = false;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weatherbit.io/v2.0/forecast/daily?city="+i.getLocation()+"&key=bba96cd3cf21434986430d7bb541bbfa")
                    .get()
                    .build();

            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject json = new JSONObject(responseBody.string());
            float temp_wind = 0;
            float temp_temp = 0;
            for (int j = 0; j < json.getJSONArray("data").length(); j++) {
                String date_temp = json.getJSONArray("data").getJSONObject(j).get("valid_date").toString();
                if(date_temp.equals(date)){
                    i.setWind_speed(json.getJSONArray("data").getJSONObject(j).get("wind_spd").toString());
                    i.setTemp(json.getJSONArray("data").getJSONObject(j).get("temp").toString());
                    temp_wind = Float.parseFloat(i.getWind_speed());
                    temp_temp = Float.parseFloat(i.getTemp());
                    if(temp_wind >=5 || temp_wind <=18 || temp_temp >=5 || temp_temp <= 35) {
                        i.setIs_good(true);
                    }
                    flag=true;
                }
            }
            if(!flag) {
                //throw new IllegalArgumentException("The given date is not in the range of the weather forecast!!");
            }
            i.setResult(temp_wind*3+temp_temp);
            if (i.getResult() > max && i.getIs_good())
            {
                max = i.getResult();
                weather = i;
            }
        }
        */