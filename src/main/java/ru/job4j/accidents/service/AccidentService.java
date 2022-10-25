package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.NoSuchElementException;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentMem;

    public Collection<Accident> findAll() {
        return (Collection<Accident>) accidentMem.findAll();
    }

    public void add(Accident accident) {
        accidentMem.save(accident);
    }

    public void update(Accident accident) {
        accidentMem.save(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
