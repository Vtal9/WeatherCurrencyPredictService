package edu.phystech.weather_currency_predict_service.controllers;

import edu.phystech.weather_currency_predict_service.services.CurrencyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency-service")
    public List<Double> getCurrencyHistory(@RequestParam @Min(1) int n) {
        return currencyService.getCurrencyData(n);
    }
}