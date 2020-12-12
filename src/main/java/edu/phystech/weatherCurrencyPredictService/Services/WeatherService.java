package edu.phystech.weatherCurrencyPredictService.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.phystech.weatherCurrencyPredictService.DataBase.Entities.WeatherData;
import edu.phystech.weatherCurrencyPredictService.DataBase.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private static final String apiKey = "8a9fee59aa08426bbd0164547202211";
    private static final String apiBaseURL = "http://api.weatherapi.com/v1";
    private static final String apiHistoryMethod = "/history.json";
    private static final String apiForecastMethod = "/forecast.json";

    private static final String DEFAULT_CITY = "Moscow";

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(RestTemplateBuilder builder, @Autowired ObjectMapper objectMapper,
                          @Autowired WeatherRepository weatherRepository) {
        restTemplate = builder.build();
        mapper = objectMapper;
        this.weatherRepository = weatherRepository;
    }

    public List<WeatherData> getWeatherDataHistory(int n) {
        return getWeatherData(n, DEFAULT_CITY);
    }

    public List<WeatherData> getWeatherDataHistory(int n, String city) {
        return getWeatherData(n, city);
    }

    private List<WeatherData> getWeatherData(int n, String city) {
        LocalDate today = LocalDate.now();
        List<WeatherData> weatherList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);

            Optional<WeatherData> weatherData = weatherRepository.findByDateAndCity(LocalDateToDate(day), city);
            if (weatherData.isPresent()) {
                weatherList.add(weatherData.get());
            } else {
                ResponseEntity<String> response = restTemplate.getForEntity(createRequestString(day, city), String.class);
                WeatherData weather = parseWeatherData(response, LocalDateToDate(day), city);
                weatherList.add(weather);
                weatherRepository.save(weather);
            }
        }
        return weatherList;
    }

    private WeatherData parseWeatherData(ResponseEntity<String> response, Date date, String city) {
        JsonNode json;
        try {
            json = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            return null;
        }
        return new WeatherData(json, date, city);
    }

    private String createRequestString(LocalDate date, String city) {
        return apiBaseURL + apiHistoryMethod + "?key=" + apiKey + "&q=" + city + "&dt=" +
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String createForecastRequestString(String city) {
        return apiBaseURL + apiForecastMethod + "?key=" + apiKey + "&q=" + city + "&days=1";
    }

    public WeatherData getForecastForTomorrow() {
        return getForecastData(DEFAULT_CITY);
    }

    public WeatherData getForecastForTomorrow(String city) {
        return getForecastData(city);
    }

    public WeatherData getForecastData(String city) {
        ResponseEntity<String> response = restTemplate.getForEntity(createForecastRequestString(city), String.class);
        return parseWeatherData(response, LocalDateToDate(LocalDate.now().plusDays(1)), city);
    }

    private Date LocalDateToDate(LocalDate date){
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
