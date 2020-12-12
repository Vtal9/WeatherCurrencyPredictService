package edu.phystech.weather_currency_predict_service.database;

import edu.phystech.weather_currency_predict_service.database.entities.WeatherData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherData, Long> {
    Optional<WeatherData> findByDateAndCity(Date date, String city);
}
