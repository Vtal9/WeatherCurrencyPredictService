package edu.phystech.weatherCurrencyPredictService.Controllers;


import edu.phystech.weatherCurrencyPredictService.Services.WeatherService;
import edu.phystech.weatherCurrencyPredictService.WeatherData;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;


    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/Weather-service")
    public List<WeatherData> getWeatherHistory(@RequestParam @NonNull int n, @RequestParam String city) {
        if (city == null) {
            return weatherService.getWeatherDataHistory(n);
        }
        return weatherService.getWeatherDataHistory(n, city);
    }
}
