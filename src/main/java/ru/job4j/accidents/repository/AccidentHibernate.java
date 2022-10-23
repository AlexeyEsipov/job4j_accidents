package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final CrudRepository crudRepository;

    public Collection<Accident> findAll() {
        return crudRepository.query(
                "SELECT DISTINCT accident FROM Accident accident "
                        + "JOIN FETCH accident.type "
                        + "JOIN FETCH accident.rules", Accident.class);
    }

    public void add(Accident accident) {
       crudRepository.run(
               session -> session.save(accident)
       );
    }

    public void update(Accident accident) {
        crudRepository.run(
                session -> session.merge(accident)
        );
    }

    public Accident findById(int id) {
        return crudRepository.optional("SELECT DISTINCT accident FROM Accident accident "
                        + "JOIN accident.type "
                        + "JOIN FETCH accident.rules "
                + "where accident.id = :fId",
                Accident.class, Map.of("fId", id)).orElseThrow(NoSuchElementException::new);
    }
}
