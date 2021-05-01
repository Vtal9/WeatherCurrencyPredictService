package edu.phystech.currencyservice.database;

import edu.phystech.currencyservice.database.entities.Valcurs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ValcursRepository extends CrudRepository<Valcurs, Long> {
    Optional<List<Valcurs>> findByDate(Date date);
}
