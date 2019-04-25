package router.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import router.connectors.FamilyConnection;
import router.models.Family;
import router.models.Person;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //runs MockitoAnnotations.initMocks(this);
public class TestJsonController {

    @InjectMocks //Request to inject mocks into the HomeController instance
    private JsonController jsonController = new JsonController();

    @Mock //Mockito will create a mock version and inject it into HomeController.
    private FamilyConnection fc;

    @Test
    public void testJsonControllerRetrieveFamilyBack() {
        when(fc.getFamily(1)).thenReturn(getMockFamily(1));
        Family result = jsonController.retrieveFamilyBack(1);
        assertEquals(1, result.getId());
        assertEquals("52 Festive Road", result.getAddress1());
        assertEquals(4, result.getPersons().size());
    }

    @Test
    public void testJsonControllerGetFamily() {
        when(fc.getStubFamily(1)).thenReturn(getMockFamily(1));
        Family result = jsonController.retrieveFamily(1);
        assertEquals(1, result.getId());
        assertEquals("52 Festive Road", result.getAddress1());
        assertEquals(4, result.getPersons().size());
    }



    public Family getMockFamily(long familyId){
        if (familyId <= 0) {return null;}

        Person p1 = new Person(1, "Dave",  "Brown", 15);
        Person p2 = new Person(2, "Sally", "Brown", 17);
        Person p3 = new Person(3, "Doreen","Smith", 45);
        Person p4 = new Person(4, "Brian", "Brown", 49);
        HashSet<Person> people = new HashSet<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);

        return new Family(familyId, "52 Festive Road", "London", people);
    }
}
