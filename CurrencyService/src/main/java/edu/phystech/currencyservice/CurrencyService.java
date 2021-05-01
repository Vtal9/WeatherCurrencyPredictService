package edu.phystech.currencyservice;


import edu.phystech.currencyservice.database.ValcursRepository;
import edu.phystech.currencyservice.database.ValuteRepository;
import edu.phystech.currencyservice.database.entities.Valcurs;
import edu.phystech.currencyservice.database.entities.Valute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableDiscoveryClient
@SpringBootApplication
public class CurrencyService {
    private static final String API_BASE_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    private final ValcursRepository valCursRepository;
    private final ValuteRepository valuteRepository;

    public CurrencyService(@Autowired ValcursRepository valCursRepository, ValuteRepository valuteRepository) {
        this.valCursRepository = valCursRepository;
        this.valuteRepository = valuteRepository;
        valCursRepository.deleteAll();
    }

    public List<Double> getCurrencyData(int n) {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate today = LocalDate.now();
        List<Double> currencyList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            LocalDate day = today.minusDays(i);

            Optional<List<Valcurs>> valCurs = valCursRepository.findByDate(localDateToDate(day));
            if (valCurs.isPresent()) {
                currencyList.add(valCurs.get().get(0).getValute("Доллар США").getValue());
            } else {
                System.out.println(currencyList.toString());
                ResponseEntity<Valcurs> response = restTemplate.getForEntity(createRequestString(day), Valcurs.class);
                System.out.println(day);
                if (!response.hasBody()) {
                    currencyList.add(null);
                } else {
                    currencyList.add(response.getBody().getValute("Доллар США").getValue());
                    System.out.println(currencyList);
                    System.out.println(response.getBody().getValuteList());
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
