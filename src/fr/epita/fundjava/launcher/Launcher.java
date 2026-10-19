package fr.epita.fundjava.launcher;

import fr.epita.fundjava.tests.*;

/**
 * Entry point of the practical work.
 * Each exercise adds a call to its Test class here.
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("Launcher started");

        TestDMO2.test();

        TestSER1.test();
        TestSER2.test();
        TestSER3.test();
        TestBLI1.test();
        TestJDB1.test();
        TestJDB2.test();
    }
}