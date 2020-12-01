package edu.phystech.weatherCurrencyPredictService.Services;


import edu.phystech.weatherCurrencyPredictService.CurrencyData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    private final String apiBaseURL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    public List<Double> getCurrencyData(int n) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();
        List<Double> currencyList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);
            ResponseEntity<String> response = restTemplate.getForEntity(createRequestString(day), String.class);
            if (!response.hasBody()) {
                currencyList.add(null);
            } else {
                CurrencyData currencyData = new CurrencyData(response);
                double currency = currencyData.asDouble();
                currencyList.add(currency);
            }
        }

        return currencyList;
    }


    private String createRequestString(LocalDate date) {
        return apiBaseURL + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
