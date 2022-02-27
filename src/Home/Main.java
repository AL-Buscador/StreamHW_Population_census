package Home;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Samuel", "Harry", "George", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);
        System.out.println();

        List<String> recruits = persons.stream()
                .filter(person -> person.getAge() == 18)
                .filter(person -> person.getSex() != Sex.WOMAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruits);
        System.out.println();

        List<String> works = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 60)
                .filter(person -> person.education == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::toString)
                .collect(Collectors.toList());
        System.out.println(works);
    }
}