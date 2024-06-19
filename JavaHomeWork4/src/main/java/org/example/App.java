package org.example;

@GenerateClass(className = "GeneratedPerson", methodName = "sayHello")
public class App {
    private Person person;

    public App(Person person) {
        this.person = person;
    }

    @ValidateAge(min = 18, max = 65)
    public int getPersonAge() {
        return person.getAge();
    }

    public static void main(String[] args) {
        Person person = new Person("John", 20);
        App main = new App(person);

        try {
            Validator.validate(main);
            System.out.println("Validation passed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
