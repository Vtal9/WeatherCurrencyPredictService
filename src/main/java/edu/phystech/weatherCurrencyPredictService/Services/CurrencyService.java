package edu.phystech.weatherCurrencyPredictService.Services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CurrencyService {
    private final String apiBaseURL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    public List<Double> getCurrencyData(int n) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();
        List<Double> currencyList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);
            double currency = parseCurrencyData(day, restTemplate);
            if (currency > 0) {
                currencyList.add(currency);
            } else {
                currencyList.add(null);
            }
        }
        return currencyList;
    }

    private double parseCurrencyData(LocalDate day, RestTemplate restTemplate) {
        ResponseEntity<String> response = restTemplate.getForEntity(CreateRequestString(day), String.class);
        if (!response.hasBody()) {
            return -1;
        }
        String regex = "Доллар США</Name><Value>([0-9]*.[0-9]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(response.getBody());
        matcher.find();
        String currency = matcher.group(1);
        return parseCurrencyAsDouble(currency);
    }

    private double parseCurrencyAsDouble(String currency) {
        Pattern formatToDoublePattern = Pattern.compile(",");
        Matcher formatToDoubleMatcher = formatToDoublePattern.matcher(currency);
        return Double.parseDouble(formatToDoubleMatcher.replaceFirst("."));
    }

    private String CreateRequestString(LocalDate date) {
        return apiBaseURL + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
