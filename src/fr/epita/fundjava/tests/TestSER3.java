package fr.epita.fundjava.tests;

import fr.epita.fundjava.dao.PersonCSVDAO;
import fr.epita.fundjava.datamodel.Person;

import java.util.List;

public class TestSER3 {

    public static void test() {
        PersonCSVDAO dao = new PersonCSVDAO("data/data.csv");

        // Read the persons, then write them back with the new column order
        List<Person> persons = dao.readAll();
        dao.writeAll(persons);

        System.out.println("Wrote " + persons.size() + " persons to data/data_output.csv");
    }
}