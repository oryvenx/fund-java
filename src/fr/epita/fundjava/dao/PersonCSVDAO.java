package fr.epita.fundjava.dao;

import fr.epita.fundjava.datamodel.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Reads persons from a CSV file and writes them to another one.
 * Input columns:  "Name", "Sex", "Age", "Height (in)", "Weight (lbs)".
 * Output columns: "Name", "Height (in)", "Weight (lbs)", "Age", "Sex".
 */
public class PersonCSVDAO {

    private static final String SEPARATOR = ",";
    private static final String DEFAULT_OUTPUT_PATH = "data/data_output.csv";
    private static final String OUTPUT_HEADER =
            "\"Name\", \"Height (in)\", \"Weight (lbs)\", \"Age\", \"Sex\"";

    private final String inputPath;
    private final String outputPath;

    /** Reads from the given file and writes to the default output file. */
    public PersonCSVDAO(String inputPath) {
        this(inputPath, DEFAULT_OUTPUT_PATH);
    }

    public PersonCSVDAO(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * Reads every person of the input file, sorted by height (smallest first).
     * Persons with the same height keep their order in the file.
     */
    public List<Person> readAll() {
        List<Person> persons = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputPath));
            // Index 0 is the header line, so the data starts at index 1
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (!line.isBlank()) {
                    persons.add(parseLine(line));
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + inputPath, e);
        }
        // List.sort is stable: equal heights are not reordered
        persons.sort(Comparator.comparingInt(Person::getHeight));
        return persons;
    }

    /**
     * Writes the given persons to the output file, with the columns in the order
     * Name, Height, Weight, Age, Sex. An existing file is overwritten.
     */
    public void writeAll(List<Person> persons) {
        List<String> lines = new ArrayList<>();
        lines.add(OUTPUT_HEADER);
        for (Person person : persons) {
            lines.add(toCsvLine(person));
        }
        try {
            Files.write(Paths.get(outputPath), lines);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot write " + outputPath, e);
        }
    }

    /** Converts one CSV line of the input file into a Person. */
    private Person parseLine(String line) {
        String[] parts = line.split(SEPARATOR);
        return new Person(
                clean(parts[0]),                    // name
                clean(parts[1]),                    // sex
                Integer.parseInt(clean(parts[2])),  // age
                Integer.parseInt(clean(parts[3])),  // height (in)
                Integer.parseInt(clean(parts[4]))); // weight (lbs)
    }

    /** Converts a Person into one CSV line of the output file (text values quoted). */
    private String toCsvLine(Person person) {
        return "\"" + person.getName() + "\", "
                + person.getHeight() + ", "
                + person.getWeight() + ", "
                + person.getAge() + ", "
                + "\"" + person.getSex() + "\"";
    }

    /** Removes the surrounding spaces and the double quotes of a value. */
    private String clean(String value) {
        return value.trim().replace("\"", "");
    }
}