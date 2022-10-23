package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcTemplate;

import java.util.Collection;

@ThreadSafe
@Service
@AllArgsConstructor
public class RuleService {
    private final RuleJdbcTemplate ruleMem;

    public Collection<Rule> findAll() {
        return ruleMem.findAll();
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }
}
