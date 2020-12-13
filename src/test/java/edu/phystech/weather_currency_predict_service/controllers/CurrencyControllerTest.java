package edu.phystech.weather_currency_predict_service.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class CurrencyControllerTest {
    @Autowired
    private CurrencyController currencyController;

    @Test
    void getCurrencyHistory() throws Exception {
        MockMvc mvc = standaloneSetup(currencyController).build();

        mvc.perform(MockMvcRequestBuilders.get("/currency-service?n=1"))
                .andExpect(status().isOk());
    }
}