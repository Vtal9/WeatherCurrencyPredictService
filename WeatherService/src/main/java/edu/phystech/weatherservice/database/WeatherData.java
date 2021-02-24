package edu.phystech.weatherservice.database;

import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Weather")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double maxTemperature;
    private double minTemperature;
    private double avgTemperature;
    private int avgHumidity;
    private double maxWind;
    private String city;

    public WeatherData(Date date, double maxTemperature, double minTemperature, double avgTemperature, int avgHumidity, double maxWind, String city) {
        this.date = date;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.maxWind = maxWind;
        this.city = city;
    }

    public WeatherData(JsonNode json, Date date, String city) {
        if (json.get("forecast") != null) {
            json = json.get("forecast").get("forecastday").get(0).get("day");
        }
        maxTemperature = json.get("maxtemp_c").asDouble();
        minTemperature = json.get("mintemp_c").asDouble();
        avgTemperature = json.get("avgtemp_c").asDouble();
        avgHumidity = json.get("avghumidity").asInt();
        maxWind = json.get("maxwind_kph").asDouble();
        this.date = date;
        this.city = city;
    }

    public WeatherData() {
    }


    @Override
    public String toString() {
        return "WeatherData{" +
                ", date=" + date +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", avgTemperature=" + avgTemperature +
                ", avgHumidity=" + avgHumidity +
                ", maxWind=" + maxWind +
                ", city='" + city + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public String getCity() {
        return city;
    }
}
