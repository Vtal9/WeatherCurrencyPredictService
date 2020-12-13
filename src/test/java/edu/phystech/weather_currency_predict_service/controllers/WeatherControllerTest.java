package edu.phystech.weather_currency_predict_service.controllers;

import edu.phystech.weather_currency_predict_service.database.entities.WeatherData;
import edu.phystech.weather_currency_predict_service.services.WeatherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class WeatherControllerTest {
    @Autowired
    private WeatherController weatherController;


    @Test
    void getWeatherHistory() throws Exception {
        MockMvc mvc = standaloneSetup(weatherController).build();

        mvc.perform(MockMvcRequestBuilders.get("/weather-service?n=1"))
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherHistoryInCity() throws Exception {
        MockMvc mvc = standaloneSetup(weatherController).build();

        mvc.perform(MockMvcRequestBuilders.get("/weather-service?n=1&city=Berlin"))
                .andExpect(status().isOk());
    }
}