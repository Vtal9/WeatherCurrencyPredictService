package edu.phystech.weather_currency_predict_service.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;

    @Test
    void getCurrencyData() {
        assertEquals(7, currencyService.getCurrencyData(7).size());
    }
}