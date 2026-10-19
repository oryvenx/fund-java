package fr.epita.fundjava.service;

import fr.epita.fundjava.datamodel.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Statistics and filtering on a list of persons.
 */
public class PersonDataService {

    /**
     * Calculates the mean age of the given persons (decimals are dropped).
     *
     * @return the mean age, or 0 when the list is empty
     */
    public int averageAge(List<Person> persons) {
        if (persons.isEmpty()) {
            return 0; // avoids a division by zero
        }
        int totalAge = 0;
        for (Person person : persons) {
            totalAge += person.getAge();
        }
        return totalAge / persons.size();
    }

    /**
     * Returns the persons who are strictly older than the threshold age.
     * A person whose age is equal to the threshold is not kept.
     */
    public List<Person> filter(List<Person> persons, int thresholdAge) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() > thresholdAge) {
                result.add(person);
            }
        }
        return result;
    }

    /**
     * Returns the year of birth of a person: current year minus age.
     * This is approximate, since the birthday within the year is unknown.
     */
    public int calculateYearOfBirth(Person person) {
        return LocalDate.now().getYear() - person.getAge();
    }
}