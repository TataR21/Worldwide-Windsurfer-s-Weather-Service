package com.example.Worldwide.Windsurfer.s.Weather.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

	public static HashMap<String, HashMap<String, WeatherTable>> location = new HashMap<>();
	public static HashMap<String, HashMap<String, WeatherTable>> getLocation() {
		return location;
	}

	public static void setLocationnn(HashMap<String, HashMap<String, WeatherTable>> locationnn) {
		Application.location = locationnn;
	}


	public static void main(String[] args) {

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.scheduleAtFixedRate(new Runnable() {
			@lombok.SneakyThrows
			@Override
			public void run() {
				location = WeatherTable.getDataFromApi();
			}
		}, 0, 30, TimeUnit.MINUTES);
		SpringApplication.run(Application.class, args);
	}

}
