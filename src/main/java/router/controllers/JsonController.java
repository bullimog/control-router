package router.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import router.models.Family;
import router.models.Person;

import java.util.HashSet;

@RestController
public class JsonController {
    @GetMapping("/family/{familyId}")
    public Family retrieveCoursesForStudent(@PathVariable long familyId) {
        Person p1 = new Person(1, "Dave", "Brown", 15);
        Person p2 = new Person(2, "Sally", "Brown", 17);
        Person p3 = new Person(3, "Doreen", "Smith", 45);
        Person p4 = new Person(4, "Brian", "Brown", 49);
        HashSet<Person> people = new HashSet<Person>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);

        Family family= new Family(familyId, "Add1", "add2", people);
        return family;
    }
}
