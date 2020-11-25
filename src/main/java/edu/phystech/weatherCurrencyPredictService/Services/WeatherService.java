package edu.phystech.weatherCurrencyPredictService.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.phystech.weatherCurrencyPredictService.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private final String apiKey = "8a9fee59aa08426bbd0164547202211";
    private final String apiBaseURL = "http://api.weatherapi.com/v1";
    private final String apiHistoryMethod = "/history.json";
    private final String apiForecastMethod = "/forecast.json";

    private final String DEFAULT_CITY = "Moscow";

    public List<WeatherData> getWeatherDataHistory(int n) {
        return getWeatherData(n, DEFAULT_CITY);
    }

    public List<WeatherData> getWeatherDataHistory(int n, String city) {
        return getWeatherData(n, city);
    }

    private List<WeatherData> getWeatherData(int n, String city) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();
        List<WeatherData> weatherList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);
            ResponseEntity<String> response = restTemplate.getForEntity(CreateRequestString(day, city), String.class);
            WeatherData weather = parseWeatherData(response);
            weatherList.add(weather);
        }
        return weatherList;
    }

    private WeatherData parseWeatherData(ResponseEntity<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json;
        try {
            json = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            return null;
        }
        return new WeatherData(json);
    }

    private String CreateRequestString(LocalDate date, String city) {
        return apiBaseURL + apiHistoryMethod + "?key=" + apiKey + "&q=" + city + "&dt=" +
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String CreateForecastRequestString(String city) {
        return apiBaseURL + apiForecastMethod + "?key=" + apiKey + "&q=" + city + "&days=1";
    }

    public WeatherData getForecastForTomorrow() {
        return getForecastData(DEFAULT_CITY);
    }

    public WeatherData getForecastForTomorrow(String city) {
        return getForecastData(city);
    }

    public WeatherData getForecastData(String city) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(CreateForecastRequestString(city), String.class);
        return parseWeatherData(response);
    }

}
