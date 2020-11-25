package edu.phystech.weatherCurrencyPredictService;

import com.fasterxml.jackson.databind.JsonNode;

public class WeatherData {
    private final double maxTemperature;
    private final double minTemperature;
    private final double avgTemperature;
    private final int avgHumidity;
    private final double maxWind;

    public WeatherData(double maxTemperature, double minTemperature, double avgTemperature, int avgHumidity, double maxWind) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.maxWind = maxWind;
    }

    public WeatherData(JsonNode json) {
        if (json.get("forecast") != null) {
            json = json.get("forecast").get("forecastday").get(0).get("day");
        }
        maxTemperature = json.get("maxtemp_c").asDouble();
        minTemperature = json.get("mintemp_c").asDouble();
        avgTemperature = json.get("avgtemp_c").asDouble();
        avgHumidity = json.get("avghumidity").asInt();
        maxWind = json.get("maxwind_kph").asDouble();

    }


    @Override
    public String toString() {
        return "WeatherData{" +
                "maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", avgTemperature=" + avgTemperature +
                ", avgHumidity=" + avgHumidity +
                ", maxWind=" + maxWind +
                '}';
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
}
