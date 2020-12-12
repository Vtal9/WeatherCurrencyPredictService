package edu.phystech.weatherCurrencyPredictService.Services;


import edu.phystech.weatherCurrencyPredictService.DataBase.Entities.ValCurs;
import edu.phystech.weatherCurrencyPredictService.DataBase.ValCursRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CurrencyService {
    private final String apiBaseURL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    private final ValCursRepository valCursRepository;

    public CurrencyService(@Autowired ValCursRepository valCursRepository) {
        this.valCursRepository = valCursRepository;
    }

    public List<Double> getCurrencyData(int n) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();
        List<Double> currencyList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);

            Optional<ValCurs> valCurs = valCursRepository.findByDate(LocalDateToDate(day));
            if (valCurs.isPresent()) {
                currencyList.add(valCurs.get().getValute("Доллар США").getValue());
            } else {
                ResponseEntity<ValCurs> response = restTemplate.getForEntity(createRequestString(day), ValCurs.class);
                if (!response.hasBody()) {
                    currencyList.add(null);
                } else {
                    currencyList.add(response.getBody().getValute("Доллар США").getValue());
                    System.out.println(response.getBody());
                    valCursRepository.save(response.getBody());
                }
            }
        }

        return currencyList;
    }


    private String createRequestString(LocalDate date) {
        return apiBaseURL + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private Date LocalDateToDate(LocalDate date){
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
