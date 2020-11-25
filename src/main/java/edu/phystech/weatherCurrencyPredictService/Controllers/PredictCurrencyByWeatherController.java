package edu.phystech.weatherCurrencyPredictService.Controllers;

import edu.phystech.weatherCurrencyPredictService.Services.PredictCurrencyByWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictCurrencyByWeatherController {
    private final PredictCurrencyByWeatherService predictCurrencyByWeatherService;


    public PredictCurrencyByWeatherController(PredictCurrencyByWeatherService predictCurrencyByWeatherService) {
        this.predictCurrencyByWeatherService = predictCurrencyByWeatherService;
    }

    @GetMapping("/predict")
    public String makePredictForTomorrow(){
        return "Predicted currency for tomorrow: " + predictCurrencyByWeatherService.predict();
    }
}
