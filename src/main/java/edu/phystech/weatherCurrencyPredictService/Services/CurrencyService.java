package edu.phystech.weatherCurrencyPredictService.Services;


import edu.phystech.weatherCurrencyPredictService.ValCurs;
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
            ResponseEntity<ValCurs> response = restTemplate.getForEntity(createRequestString(day), ValCurs.class);
            System.out.println(response.getBody().getValuteList().get(0).getValue());
            if (!response.hasBody()) {
                currencyList.add(null);
            } else {

                currencyList.add(response.getBody().getValute("Доллар США").getValue());
            }
        }

        return currencyList;
    }


    private String createRequestString(LocalDate date) {
        return apiBaseURL + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
