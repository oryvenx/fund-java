package fr.epita.fundjava.tests;

import fr.epita.fundjava.dao.PersonCSVDAO;
import fr.epita.fundjava.dao.PersonJDBCDAO;
import fr.epita.fundjava.datamodel.Person;

import java.util.List;

public class TestJDB2 {

    /**
     * The PERSONS table must already exist: TestJDB1.test() creates it,
     * so it has to be called before this method (as the Launcher does).
     */
    public static void test() {
        // 1. Read the persons from the CSV file
        List<Person> persons = new PersonCSVDAO("data/data.csv").readAll();

        // 2. Insert each person in the database
        PersonJDBCDAO dao = new PersonJDBCDAO();
        for (Person person : persons) {
            dao.create(person);
        }
        System.out.println("Inserted " + persons.size() + " persons from the CSV file");

        // 3. Search to check that everything was inserted
        List<Person> stored = dao.search();

        // 4. Display the result list
        System.out.println("Found " + stored.size() + " persons in the database:");
        stored.forEach(System.out::println);

        // Extra check of the criteria search: only the women
        List<Person> women = dao.search(new Person(null, "F", 0, 0, 0));
        System.out.println("Women found: " + women.size());
    }
}