package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final RowMapper<Rule> ruleRowMapper = (rs, row) -> new Rule(
            rs.getInt("id"),
            rs.getString("name"));

    public List<Rule> findAll() {
        return jdbc.query("SELECT id, name FROM rule",
                ruleRowMapper);
    }

    public Rule findById(int id) {
        return jdbc.queryForObject("SELECT * from rule where id = ?",
                ruleRowMapper, id);
    }
}
