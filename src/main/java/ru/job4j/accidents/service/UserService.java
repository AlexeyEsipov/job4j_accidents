package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

import java.util.Optional;
@ThreadSafe
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> save(User user) {
        try {
            return Optional.of(userRepository.save(user));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
