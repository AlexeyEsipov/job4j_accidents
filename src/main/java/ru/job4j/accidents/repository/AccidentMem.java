package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

@Repository
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMem() {
        AccidentType at = new AccidentType(1, "Две машины");
        accidents.put(1, new Accident(1, "name1", "text1", "address1", at));
        accidents.put(2, new Accident(2, "name2", "text2", "address2", at));
        accidents.put(3, new Accident(3, "name3", "text3", "address3", at));
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
