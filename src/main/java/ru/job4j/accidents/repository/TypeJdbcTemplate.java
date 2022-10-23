package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class TypeJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final RowMapper<AccidentType> typeRowMapper =
            (rs, row) -> new AccidentType(
            rs.getInt("id"),
            rs.getString("name"));

    public List<AccidentType> findAll() {
        return jdbc.query("SELECT id, name FROM type",
                typeRowMapper);
    }

    public AccidentType findById(int id) {
        return jdbc.queryForObject("SELECT * FROM type WHERE id = ?",
                typeRowMapper,
                id);
    }
}
