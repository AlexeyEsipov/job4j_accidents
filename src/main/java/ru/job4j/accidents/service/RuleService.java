package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.NoSuchElementException;

@ThreadSafe
@Service
@AllArgsConstructor
public class RuleService {
    private final RuleRepository ruleMem;

    public Collection<Rule> findAll() {
        return (Collection<Rule>) ruleMem.findAll();
    }

    public Rule findById(int id) {
        return ruleMem.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
