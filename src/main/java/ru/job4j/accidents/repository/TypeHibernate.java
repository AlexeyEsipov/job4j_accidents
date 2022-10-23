package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import ru.job4j.accidents.model.AccidentType;

@Repository
@AllArgsConstructor
public class TypeHibernate {
    private final CrudRepository crudRepository;

    public List<AccidentType> findAll() {
        return crudRepository.query("FROM AccidentType", AccidentType.class);
    }

    public AccidentType findById(int id) {
        return crudRepository.optional("SELECT type FROM AccidentType type WHERE type.id = :fId",
                AccidentType.class, Map.of("fId", id)).orElseThrow(NoSuchElementException::new);
    }
}
