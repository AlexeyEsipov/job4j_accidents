package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMem() {
        AccidentType at = new AccidentType(1, "Две машины");
        Rule rule1 = new Rule(1, "Статья. 1");
        Rule rule2 = new Rule(2, "Статья. 2");
        Rule rule3 = new Rule(3, "Статья. 3");
        Set<Rule> rules = new HashSet<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);
        accidents.put(1, new Accident(1, "name1", "text1", "address1", at, rules));
        accidents.put(2, new Accident(2, "name2", "text2", "address2", at, rules));
        accidents.put(3, new Accident(3, "name3", "text3", "address3", at, rules));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void add(Accident accident) {
        accident.setId(ids.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
