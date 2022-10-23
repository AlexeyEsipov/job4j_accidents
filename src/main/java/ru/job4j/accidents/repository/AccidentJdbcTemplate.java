package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Accident;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final TypeJdbcTemplate typeJdbcTemplate;
    private final RuleJdbcTemplate ruleJdbcTemplate;
    private final AccidentRuleJdbcTemplate accRuleJdbcTemplate;

    public Collection<Accident> findAll() {
        return jdbc.query("SELECT * FROM accident",
                (rs, row) -> {
                    int id = rs.getInt("id");
                    Accident accident = new Accident(
                            id,
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address"),
                            typeJdbcTemplate.findById(rs.getInt("type_id")));
                    accident.setRules(getRules(id));
                    return accident;
                }
        );
    }

    public void add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO accident (name, text, address, type_id)"
                            + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, keyHolder);
        int id;
        if (keyHolder.getKeys().size() > 1) {
            id = (int) keyHolder.getKeys().get("id");
        } else {
            id = keyHolder.getKey().intValue();
        }
        accRuleJdbcTemplate.saveRule(accident.getRules(), id);
    }

    public void update(Accident accident) {
        int id = accident.getId();
        jdbc.update("UPDATE accident SET "
                        + "name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), id);
        accRuleJdbcTemplate.updateAccidentRules(accident.getRules(), id);
    }

    public Accident findById(int id) {
        Accident result = jdbc.queryForObject("SELECT * FROM accident WHERE id = ?",
                (resultSet, rowNum) -> new Accident(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("address"),
                        typeJdbcTemplate.findById(resultSet.getInt("type_id"))), id);
        boolean isNotNull = result != null;
        if (isNotNull) {
            result.setRules(getRules(id));
        }
        return result;
    }

    private Set<Rule> getRules(int accidentId) {
        Set<Rule> result = new HashSet<>();
        List<Integer> listRuleIds = accRuleJdbcTemplate.getRuleIds(accidentId);
        for (int id : listRuleIds) {
            result.add(ruleJdbcTemplate.findById(id));
        }
        return result;
    }
}
