package edu.phystech.currencyservice.database;

import edu.phystech.currencyservice.database.entities.ValCurs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ValCursRepository extends CrudRepository<ValCurs, Long> {
    Optional<List<ValCurs>> findByDate(Date date);
}
