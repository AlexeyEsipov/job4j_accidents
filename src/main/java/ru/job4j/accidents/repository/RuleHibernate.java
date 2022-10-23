package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import ru.job4j.accidents.model.Rule;

@Repository
@AllArgsConstructor
public class RuleHibernate {
    private final CrudRepository crudRepository;

    public List<Rule> findAll() {
        return crudRepository.query("FROM Rule", Rule.class);
    }

    public Rule findById(int id) {
        return crudRepository.optional("SELECT rule FROM Rule rule WHERE rule.id = :fId",
                Rule.class, Map.of("fId", id)).orElseThrow(NoSuchElementException::new);
    }
}
