package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeRepository;

import java.util.Collection;
import java.util.NoSuchElementException;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final TypeRepository accidentType;

    public Collection<AccidentType> findAll() {
        return (Collection<AccidentType>) accidentType.findAll();
    }

    public AccidentType findById(int id) {
        return accidentType.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
