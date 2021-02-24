package edu.phystech.currencyservice.database;

import edu.phystech.currencyservice.database.entities.Valute;
import org.springframework.data.repository.CrudRepository;

public interface ValuteRepository extends CrudRepository<Valute, Long> {

}
