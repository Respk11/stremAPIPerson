import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println(persons);
        System.out.println(persons.stream()
                .filter(person -> person.getAge() < 18).count());
        persons.stream()
                .filter(person -> Sex.MAN.equals(person.getSex()))
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("________________________________________________");
        persons.stream()
                .filter(person -> Education.HIGHER.equals(person.getEducation()))
                .filter(person -> (Sex.MAN.equals(person.getSex())&&person.getAge() > 18 && person.getAge() < 65)
                        || (Sex.WOMAN.equals(person.getSex())&&person.getAge() > 18 && person.getAge() < 60))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList()).forEach(System.out::println);
    }
}




//3. Для получения отсортированного по фамилии списка потенциально работоспособных людей с высшим образованием
// необходимо применить ряд промежуточных методов `filter()`, метод `sorted()` в который нужно будет положить компаратор
// по фамилиям `Comparator.comparing()`. Завершить стрим необходимо методом `collect()`.