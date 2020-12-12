package edu.phystech.weather_currency_predict_service.database;

import edu.phystech.weather_currency_predict_service.database.entities.ValCurs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ValCursRepository extends CrudRepository<ValCurs, Long> {
    Optional<ValCurs> findByDate(Date date);
}
