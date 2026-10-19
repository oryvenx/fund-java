package fr.epita.fundjava.tests;

import fr.epita.fundjava.datamodel.Person;

public class TestDMO2 {

    public static void test() {
        // Creates the instance that must produce: Person [name="Alex", weight="150"]
        Person person = new Person("Alex", "M", 41, 74, 150);
        System.out.println(person);
    }
}