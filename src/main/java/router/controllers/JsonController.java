package router.controllers;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import router.connectors.HttpConnection;
import router.models.Family;
import router.models.Person;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

@RestController
@RequestMapping("/myservice")
public class JsonController {

    @Autowired
    private HttpConnection connection;

    @GetMapping("/family-back/{familyId}")
    public Family retrieveFamilyBack(@PathVariable long familyId) {
        String url = "http://localhost:8080/myservice/family/22";
        String content = connection.doGet(url);

        Family f = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            f = mapper.readValue(content, Family.class);
        }
        catch(JsonMappingException ex){System.out.println("JsonMappingException:"+ex);}
        catch (IOException ex){System.out.println("IOException: " + ex);}

        return f;
    }


    private Family getFamily(long familyId){
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

    //dummy implementation
    private Family createFamily(Family family){
        return null;
    }

    @GetMapping("/family/{familyId}")
    public Family retrieveFamily(@PathVariable long familyId) {
        return getFamily(familyId);
    }


    @PostMapping("/family")
    public ResponseEntity<Family> create(@RequestBody Family family) throws URISyntaxException {
        Family createdFamily = createFamily(family);
        if (createdFamily == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdFamily.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdFamily);
        }
    }

}
