package fr.epita.fundjava.datamodel;

/**
 * Bio-characteristics of a person.
 * Sex is "M" or "F"; age, height (in inches) and weight (in pounds) are integers.
 */
public class Person {

    private String name;
    private String sex;
    private int age;
    private int height;
    private int weight;

    public Person(String name, String sex, int age, int height, int weight) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Format required by the exercise: Person [name="Alex", weight="150"]
     */
    @Override
    public String toString() {
        return "Person [name=\"" + name + "\", weight=\"" + weight + "\"]";
    }
}