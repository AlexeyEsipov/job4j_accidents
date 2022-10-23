package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeHibernate;
import java.util.Collection;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final TypeHibernate accidentType;

    public Collection<AccidentType> findAll() {
        return accidentType.findAll();
    }

    public AccidentType findById(int id) {
        return accidentType.findById(id);
    }
}
