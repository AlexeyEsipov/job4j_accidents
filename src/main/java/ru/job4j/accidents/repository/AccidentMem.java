package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "name1", "text1", "address1"));
        accidents.put(2, new Accident(2, "name2", "text2", "address2"));
        accidents.put(3, new Accident(3, "name3", "text3", "address3"));
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
