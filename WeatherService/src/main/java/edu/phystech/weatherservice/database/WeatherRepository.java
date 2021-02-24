package edu.phystech.weatherservice.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherData, Long> {
    Optional<List<WeatherData>> findByDateAndCity(Date date, String city);
}
