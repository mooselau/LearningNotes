package javaself;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        StreamDemo tester = new StreamDemo();
        tester.entrypoint();
    }

    public void entrypoint() {
        List<Person> persons = Arrays.asList(new Person("Amy", 17), new Person("Tom", 22),
                new Person("Lucy", 35));
        Person findFirst = persons.stream().findFirst().orElseThrow(RuntimeException::new);
        System.out.println("findFirst: " + findFirst.getName());

        List<Person> result = persons.stream().filter(person -> person.getAge() < 99).collect(Collectors.toList());
        System.out.println("result: " + result.size());

        for (Person p : result) {
            System.out.print("Name: " + p.getName() + ", age: " + p.getAge() + "\t");
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class Person {
        private String name;
        private int age;
    }
}
