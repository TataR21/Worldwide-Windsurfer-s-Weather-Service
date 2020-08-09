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
    RunThread runThread;

    @GetMapping("/weather")
    public Weather WeatherForm(
            @RequestParam(value = "date", required = false) String date) {
        if (date == null) {
            date = date_str;
        }
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        boolean isDate1 = date.matches(datePattern);
        if (!isDate1) {
            throw new ErrorController.DateFormatException();
        }
        location = runThread.getLocations();
        float max = 0;
        float result;
        for(Map.Entry<String, HashMap<String, WeatherTable>> entry: location.entrySet()) {
            float temp;
            float windSpeed;
            try{
                temp = entry.getValue().get(date).getTemp();
                windSpeed = entry.getValue().get(date).getWind_speed();
            } catch (Exception e) {
                throw new ErrorController.DateNotfoundException();
            }
            boolean isWindAndTempInRange = windSpeed >=5 || windSpeed <=18 || temp >=5 || temp <= 35;
            result = windSpeed*3+temp;
            if (result > max && isWindAndTempInRange)
            {
                max = result;
                weather = new Weather(entry.getKey(), temp, windSpeed, date);
            }
        }
         return weather;
    }
}