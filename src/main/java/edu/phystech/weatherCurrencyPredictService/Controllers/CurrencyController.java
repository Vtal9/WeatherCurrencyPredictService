package edu.phystech.weatherCurrencyPredictService.Controllers;

import edu.phystech.weatherCurrencyPredictService.Services.CurrencyService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency-service")
    public List<Double> getCurrencyHistory(@RequestParam @NonNull int n) {
        return currencyService.getCurrencyData(n);
    }
}
