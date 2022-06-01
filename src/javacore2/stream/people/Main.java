package javacore2.stream.people;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static javacore2.stream.people.Education.HIGHER;
import static javacore2.stream.people.Sex.Man;
import static javacore2.stream.people.Sex.Women;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        var minor = persons.parallelStream()
                .filter(person -> {
                    if (person.getAge() < 18)
                        return true;
                    return false;
                })
                .count();
        var familiesConscripts = persons.parallelStream()
                .filter(person -> {
                    if (person.getAge() > 18 && person.getAge() < 27)
                        return true;
                    return false;
                })
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        var workingPeopleWithEducation = persons.parallelStream()
                .filter(person -> person.getEducation() == HIGHER)
                .filter(person -> {
                    if(person.getSex() == Man && person.getAge() > 18 && person.getAge() < 65)
                        return true;
                    if(person.getSex() == Women && person.getAge() > 18 && person.getAge() < 60)
                        return true;
                    return false;
                })
                .sorted(comparing(person -> person.getFamily().compareTo(person.getFamily())))
                .collect(Collectors.toList());

    }
}
