package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.AuthorityRepository;

@ThreadSafe
@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }
}
