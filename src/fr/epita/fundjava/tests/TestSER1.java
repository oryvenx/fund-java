package fr.epita.fundjava.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestSER1 {

    // Relative to the project root, which is IntelliJ's default working directory
    private static final String FILE_PATH = "data/data.csv";

    public static void test() {
        try {
            // Read every line of the file in one go
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

            // Line 1 is the header, so the 2nd line is at index 1
            String secondLine = lines.get(1);
            System.out.println("2nd line of the file: " + secondLine);

            // Split into parts, then clean each part (trim() returns a NEW string,
            // so its result must be stored back into the array)
            String[] parts = secondLine.split(",");
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim().replace("\"", "");
            }
            System.out.println("Cleaned parts: " + Arrays.toString(parts));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + FILE_PATH, e);
        }
    }
}