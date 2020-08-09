package com.example.Worldwide.Windsurfer.s.Weather.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		ServiceWeatherTable serviceWeatherTable = new WeatherTable();
		RunThread runThread = new RunThread(serviceWeatherTable);

		final ScheduledFuture<?> beepHandler =
				scheduler.scheduleAtFixedRate(runThread, 0, 30, TimeUnit.MINUTES);

		SpringApplication.run(Application.class, args);
	}

}
