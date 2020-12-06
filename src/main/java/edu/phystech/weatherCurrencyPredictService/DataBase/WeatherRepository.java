package edu.phystech.weatherCurrencyPredictService.DataBase;

import edu.phystech.weatherCurrencyPredictService.DataBase.Entities.WeatherData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherData, Long> {
    Optional<WeatherData> findByDate(Date date);
}
