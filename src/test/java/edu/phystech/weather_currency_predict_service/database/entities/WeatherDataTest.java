package edu.phystech.weather_currency_predict_service.database.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {
    private static WeatherData weatherData;

    @BeforeAll
    static void setUp(){
        weatherData = new WeatherData(new Date(12345), 50., 25., 35., 5, 140., "city");
    }

    @Test
    void getDate() {
        assertEquals(new Date(12345), weatherData.getDate());
    }

    @Test
    void getMaxTemperature() {
        assertEquals(50., weatherData.getMaxTemperature());
    }

    @Test
    void getMinTemperature() {
        assertEquals(25., weatherData.getMinTemperature());
    }

    @Test
    void getAvgTemperature() {
        assertEquals(35., weatherData.getAvgTemperature());
    }

    @Test
    void getAvgHumidity() {
        assertEquals(5, weatherData.getAvgHumidity());
    }

    @Test
    void getMaxWind() {
        assertEquals(140., weatherData.getMaxWind());
    }

    @Test
    void getCity() {
        assertEquals("city", weatherData.getCity());
    }


}