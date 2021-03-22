package edu.phystech.currencyservice;


import edu.phystech.currencyservice.database.ValCursRepository;
import edu.phystech.currencyservice.database.entities.ValCurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CurrencyService {
    private static final String API_BASE_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    private final ValCursRepository valCursRepository;

    public CurrencyService(@Autowired ValCursRepository valCursRepository) {
        this.valCursRepository = valCursRepository;
        valCursRepository.deleteAll();
    }

    public List<Double> getCurrencyData(int n) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();
        List<Double> currencyList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);

            Optional<List<ValCurs>> valCurs = valCursRepository.findByDate(localDateToDate(day));
            if (valCurs.isPresent()) {
                currencyList.add(valCurs.get().get(0).getValute("Доллар США").getValue());
            } else {
                System.out.println(currencyList.toString());
                ResponseEntity<ValCurs> response = restTemplate.getForEntity(createRequestString(day), ValCurs.class);
                System.out.println(day);
                if (!response.hasBody()) {
                    currencyList.add(null);
                } else {
                    currencyList.add(response.getBody().getValute("Доллар США").getValue());
                    valCursRepository.save(response.getBody());
                    System.out.println(valCursRepository.findByDate(localDateToDate(day)));
                }
            }

        }

        return currencyList;
    }


    private String createRequestString(LocalDate date) {
        return API_BASE_URL + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private Date localDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrencyService.class, args);
    }

}
