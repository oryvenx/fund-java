package fr.epita.fundjava.dao;

import fr.epita.fundjava.datamodel.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the PERSONS table.
 */
public class PersonJDBCDAO {

    /** Inserts a person in the database. The id is generated automatically. */
    public void create(Person person) {
        String sql = "INSERT INTO PERSONS (name, sex, age, height, weight) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = ConnectionProvider.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, person.getName());
                ps.setString(2, person.getSex());
                ps.setInt(3, person.getAge());
                ps.setInt(4, person.getHeight());
                ps.setInt(5, person.getWeight());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create person " + person.getName(), e);
        }
    }

    /** Returns every person of the table, in insertion order. */
    public List<Person> search() {
        return search(new Person(null, null, 0, 0, 0));
    }

    /**
     * Searches persons with a criteria template: every text field that is not null
     * and every number greater than 0 becomes an equality condition.
     * A template with nothing set returns every person.
     */
    public List<Person> search(Person criteria) {
        StringBuilder sql = new StringBuilder(
                "SELECT name, sex, age, height, weight FROM PERSONS WHERE 1 = 1");
        List<Object> parameters = new ArrayList<>();

        if (criteria != null) {
            addCriterion(sql, parameters, "name", criteria.getName());
            addCriterion(sql, parameters, "sex", criteria.getSex());
            // For the int fields, 0 means "not set"
            addCriterion(sql, parameters, "age", criteria.getAge() > 0 ? criteria.getAge() : null);
            addCriterion(sql, parameters, "height", criteria.getHeight() > 0 ? criteria.getHeight() : null);
            addCriterion(sql, parameters, "weight", criteria.getWeight() > 0 ? criteria.getWeight() : null);
        }
        sql.append(" ORDER BY id");

        List<Person> result = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    ps.setObject(i + 1, parameters.get(i));
                }
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(new Person(
                                rs.getString("name"),
                                rs.getString("sex"),
                                rs.getInt("age"),
                                rs.getInt("height"),
                                rs.getInt("weight")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot search persons", e);
        }
        return result;
    }

    /** Adds " AND column = ?" to the query when the value is not null. */
    private void addCriterion(StringBuilder sql, List<Object> parameters, String column, Object value) {
        if (value != null) {
            sql.append(" AND ").append(column).append(" = ?");
            parameters.add(value);
        }
    }
}