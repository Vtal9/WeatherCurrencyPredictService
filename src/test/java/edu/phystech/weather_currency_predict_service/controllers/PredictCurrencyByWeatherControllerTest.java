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
class PredictCurrencyByWeatherControllerTest {
    @Autowired
    private PredictCurrencyByWeatherController controller;

    @Test
    void makePredictForTomorrow() throws Exception {
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(MockMvcRequestBuilders.get("/predict"))
                .andExpect(status().isOk());
    }
}