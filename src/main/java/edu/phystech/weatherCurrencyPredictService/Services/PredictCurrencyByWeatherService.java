package edu.phystech.weatherCurrencyPredictService.Services;

import edu.phystech.weatherCurrencyPredictService.DataBase.Entities.WeatherData;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PredictCurrencyByWeatherService {
    private final SimpleRegression model;
    private final WeatherService weatherService;
    private final CurrencyService currencyService;

    private final int PERIOD_SIZE_TO_FIT = 7;

    public PredictCurrencyByWeatherService(WeatherService weatherService, CurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
        model = new SimpleRegression();
        fit();
    }

    public  double predict(){
        WeatherData tomorrowForecast = weatherService.getForecastForTomorrow();
        return predict(tomorrowForecast);
    }

    private void fit() {
        List<WeatherData> weatherDataList = weatherService.getWeatherDataHistory(PERIOD_SIZE_TO_FIT);
        List<Double> currencyList = currencyService.getCurrencyData(PERIOD_SIZE_TO_FIT);

        List<double[]> dataset = createDataset(weatherDataList, currencyList);
        for(int i = 0; i < dataset.get(0).length; ++i) {
            model.addData(dataset.get(0)[i], dataset.get(1)[i]);
        }
    }



    private List<double[]> createDataset(List<WeatherData> weatherDataList, List<Double> currencyList) {
        List<Integer> indexes = IntStream
                .range(0, weatherDataList.size())
                .boxed()
                .filter(i -> weatherDataList.get(i) != null && currencyList.get(i) != null)
                .collect(Collectors.toList());
        double[] xs = new double[indexes.size()];
        double[] ys = new double[indexes.size()];
        for (int i = 0; i < indexes.size(); ++i) {
            ys[i] = currencyList.get(indexes.get(i));
            xs[i] = weatherDataList.get(indexes.get(i)).getAvgTemperature();
        }
        return List.of(xs, ys);
    }

    private double predict(WeatherData weatherData){
        return model.predict(weatherData.getAvgHumidity());
    }
}
