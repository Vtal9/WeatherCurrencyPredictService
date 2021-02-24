package edu.phystech.weatherservice;


import edu.phystech.weatherservice.database.WeatherData;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
public class Controller {

    private final WeatherService weatherService;


    public Controller(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Validated
    @GetMapping("/weather-service")
    public List<Double> getWeatherHistory(@RequestParam @Min(1) int n, @RequestParam @Nullable String city) {
        if (city == null) {
            return weatherService.getWeatherDataHistory(n).stream().map(WeatherData::getAvgTemperature).collect(Collectors.toList());
        }
        return weatherService.getWeatherDataHistory(n, city).stream().map(WeatherData::getAvgTemperature).collect(Collectors.toList());
    }

    @GetMapping("/forecast")
    public double getForecast(@RequestParam @Nullable String city){
        if(city == null){
            return weatherService.getForecastForTomorrow().getAvgTemperature();
        }
        return weatherService.getForecastData(city).getAvgTemperature();
    }
}
