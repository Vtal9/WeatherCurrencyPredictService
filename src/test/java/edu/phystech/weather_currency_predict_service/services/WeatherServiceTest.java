package edu.phystech.weather_currency_predict_service.services;

import edu.phystech.weather_currency_predict_service.WeatherCurrencyPredictServiceApplication;
import edu.phystech.weather_currency_predict_service.database.entities.WeatherData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    void getWeatherDataHistory() {
        assertEquals(7, weatherService.getWeatherDataHistory(7).size());
    }

    @Test
    void getWeatherDataHistoryInBerlin() {
        assertEquals(7, weatherService.getWeatherDataHistory(7, "Berlin").size());
    }

    @Test
    void testGetForecastForTomorrowInDefaultCity() {
        WeatherData forecast = weatherService.getForecastForTomorrow();
        assertEquals(forecast.getCity(), "Moscow");
        assertEquals(forecast.getDate(), localDateToDate(LocalDate.now().plusDays(1)));
    }

    @Test
    void testGetForecastForTomorrowInBerlin() {
        WeatherData forecast = weatherService.getForecastForTomorrow("Berlin");
        assertEquals(forecast.getCity(), "Berlin");
        assertEquals(forecast.getDate(), localDateToDate(LocalDate.now().plusDays(1)));
    }

    @Test
    void getForecastDataInMoscow() {
        WeatherData forecast = weatherService.getForecastData("Moscow");
        assertEquals(forecast.getCity(), "Moscow");
        assertEquals(forecast.getDate(), localDateToDate(LocalDate.now().plusDays(1)));
    }


    private Date localDateToDate(LocalDate date){
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}