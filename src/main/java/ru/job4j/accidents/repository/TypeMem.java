package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ru.job4j.accidents.model.AccidentType;

@Repository
@ThreadSafe
public class TypeMem {
    private final Map<Integer, AccidentType> accTypeMap = new ConcurrentHashMap<>();
    public TypeMem() {
        accTypeMap.put(1, new AccidentType(1, "Две машины"));
        accTypeMap.put(2, new AccidentType(2, "Машина и человек"));
        accTypeMap.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public Collection<AccidentType> findAll() {
        return accTypeMap.values();
    }

    public AccidentType findById(int id) {
        return accTypeMap.get(id);
    }
}
