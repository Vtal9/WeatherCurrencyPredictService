package edu.phystech.weather_currency_predict_service.controllers;

import edu.phystech.weather_currency_predict_service.services.PredictCurrencyByWeatherService;
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
