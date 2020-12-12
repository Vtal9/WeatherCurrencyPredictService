package edu.phystech.weather_currency_predict_service.database;

import edu.phystech.weather_currency_predict_service.database.entities.Valute;
import org.springframework.data.repository.CrudRepository;

public interface ValuteRepository extends CrudRepository<Valute, Long> {

}
