package fr.epita.fundjava.tests;

import fr.epita.fundjava.dao.PersonCSVDAO;
import fr.epita.fundjava.datamodel.Person;
import fr.epita.fundjava.service.PersonDataService;

import java.util.List;

public class TestBLI1 {

    public static void test() {
        // Load the persons from the CSV file
        List<Person> persons = new PersonCSVDAO("data/data.csv").readAll();
        PersonDataService service = new PersonDataService();

        // Use case 1: mean age
        System.out.println("Average age: " + service.averageAge(persons));

        // Use case 2: how many persons are older than 32
        System.out.println("Persons older than 32: " + service.filter(persons, 32).size());

        // Use case 3: year of birth of Alex
        Person alex = findByName(persons, "Alex");
        System.out.println("Year of birth of " + alex.getName() + ": " + service.calculateYearOfBirth(alex));
    }

    /** Looks for a person by name in the list. */
    private static Person findByName(List<Person> persons, String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        throw new IllegalStateException("No person named " + name);
    }
}