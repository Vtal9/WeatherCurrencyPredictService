package edu.phystech.currencyservice;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class Controller {
    private final CurrencyService currencyService;

    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency-service")
    public List<Double> getCurrencyHistory(@RequestParam @Min(1) int n) {
        return currencyService.getCurrencyData(n);
    }
}
