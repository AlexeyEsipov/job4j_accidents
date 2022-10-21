package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class RuleMem {
    private final Map<Integer, Rule> accRuleMap = new ConcurrentHashMap<>();
    public RuleMem() {
        accRuleMap.put(1, new Rule(1, "Статья. 1"));
        accRuleMap.put(2, new Rule(2, "Статья. 2"));
        accRuleMap.put(3, new Rule(3, "Статья. 3"));
    }

    public Collection<Rule> findAll() {
        return accRuleMap.values();
    }

    public Rule findById(int id) {
        return accRuleMap.get(id);
    }
}
