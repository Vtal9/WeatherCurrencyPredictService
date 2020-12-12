package edu.phystech.weatherCurrencyPredictService.DataBase;

import edu.phystech.weatherCurrencyPredictService.DataBase.Entities.ValCurs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ValCursRepository extends CrudRepository<ValCurs, Long> {
    Optional<ValCurs> findByDate(Date date);
}
