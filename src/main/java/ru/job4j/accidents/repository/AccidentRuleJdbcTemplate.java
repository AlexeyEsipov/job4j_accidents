package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentRuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public void saveRule(Collection<Rule> rules, int accidentId) {
        rules.forEach(rule -> jdbc.update(
                "INSERT INTO accident_rule (rule_id, accident_id) VALUES (?, ?)",
                rule.getId(), accidentId));

    }

    public List<Integer> getRuleIds(int accidentId) {
        return jdbc.query(
                "SELECT rule_id FROM accident_rule WHERE accident_id = ?",
                (resultSet, rowNum) -> resultSet.getInt("rule_id"), accidentId);
    }

    public void updateAccidentRules(Collection<Rule> rules, int accidentId) {
        jdbc.update("DELETE FROM accident_rule WHERE accident_id = ?", accidentId);
        saveRule(rules, accidentId);
    }
}
