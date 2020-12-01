package edu.phystech.weatherCurrencyPredictService.Controllers;


import edu.phystech.weatherCurrencyPredictService.Services.WeatherService;
import edu.phystech.weatherCurrencyPredictService.WeatherData;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class WeatherController {

    private final WeatherService weatherService;


    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather-service")
    public List<WeatherData> getWeatherHistory(@RequestParam @Min(1) int n, @RequestParam @Nullable String city) {
        if (city == null) {
            return weatherService.getWeatherDataHistory(n);
        }
        return weatherService.getWeatherDataHistory(n, city);
    }
}
