package fr.epita.fundjava.tests;

import fr.epita.fundjava.dao.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDB1 {

    public static void test() {
        // One column per Person attribute, plus an auto-incremented id
        String createTable = "CREATE TABLE IF NOT EXISTS PERSONS ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "sex CHAR(1) NOT NULL CHECK (sex IN ('M', 'F')), "
                + "age INT NOT NULL, "
                + "height INT NOT NULL, "
                + "weight INT NOT NULL"
                + ")";

        try {
            Connection connection = ConnectionProvider.getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTable);
                System.out.println("Table PERSONS created");

                // Quick check: the table exists and is empty
                try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM PERSONS")) {
                    rs.next();
                    System.out.println("Rows in PERSONS: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("JDB1 failed", e);
        }
    }
}