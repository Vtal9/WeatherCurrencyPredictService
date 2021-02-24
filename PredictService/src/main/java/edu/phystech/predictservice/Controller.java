package edu.phystech.predictservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final PredictService predictCurrencyByWeatherService;


    public Controller(PredictService predictCurrencyByWeatherService) {
        this.predictCurrencyByWeatherService = predictCurrencyByWeatherService;
    }

    @GetMapping("/predict")
    public String makePredictForTomorrow(){
        return "Predicted currency for tomorrow: " + predictCurrencyByWeatherService.predict();
    }
}
