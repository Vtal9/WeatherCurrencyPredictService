package edu.phystech.predictservice;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class PredictService {
    private static final int PERIOD_SIZE_TO_FIT = 7;

    private static final String weatherURL = "http://weatherservice:8081/weather-service?n=" + PERIOD_SIZE_TO_FIT;
    private static final String currencyURL = "http://currencyservice:8082/currency-service?n=" + PERIOD_SIZE_TO_FIT;
    private static final String forecastURL = "http://weatherservice:8081/forecast";

    private final RestTemplate restTemplate;

    private final SimpleRegression model;
    private boolean isFitted = false;

    @Autowired
    public PredictService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
        model = new SimpleRegression();
    }

    public double predict() {
        if(!isFitted){
            fit();
            isFitted = true;
        }
        ResponseEntity<Double> forecastResponse = restTemplate.getForEntity(forecastURL, Double.class);
        return predict(forecastResponse.getBody());
    }

    private void fit() {
        ResponseEntity<double[]> weatherResponse = restTemplate.getForEntity(weatherURL, double[].class);
        ResponseEntity<double[]> currencyResponse = restTemplate.getForEntity(currencyURL, double[].class);
        if (weatherResponse.hasBody() && currencyResponse.hasBody()) {
            for (int i = 0; i < PERIOD_SIZE_TO_FIT; ++i) {
                model.addData(weatherResponse.getBody()[i], currencyResponse.getBody()[i]);
            }
        } else {
            if (!weatherResponse.hasBody()) {
                System.out.println("wrong weather response");
            }
            if (!currencyResponse.hasBody()) {
                System.out.println("wrong currency response");
            }
        }
    }


    private double predict(Double forecast) {
        return model.predict(forecast);
    }

    public static void main(String[] args) {
        SpringApplication.run(PredictService.class, args);
    }

}
