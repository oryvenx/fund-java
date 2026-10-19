package fr.epita.fundjava.tests;

import fr.epita.fundjava.dao.PersonCSVDAO;
import fr.epita.fundjava.datamodel.Person;

import java.util.List;

public class TestSER2 {

    public static void test() {
        PersonCSVDAO dao = new PersonCSVDAO("data/data.csv");
        List<Person> persons = dao.readAll();

        System.out.println("Persons sorted by height (" + persons.size() + "):");
        persons.forEach(System.out::println);
    }
}