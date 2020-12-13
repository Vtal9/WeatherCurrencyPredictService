package edu.phystech.weather_currency_predict_service.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PredictCurrencyByWeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CurrencyService currencyService;

    @Test
    void predict() {
        PredictCurrencyByWeatherService predictionService = new PredictCurrencyByWeatherService(weatherService, currencyService);
        double predictedValue = predictionService.predict();
        assertTrue(0 < predictedValue && predictedValue < 100);
    }
}