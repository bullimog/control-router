package router.connectors;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import router.config.FamilyConnectionConfig;
import router.models.Family;
import router.models.Person;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public class FamilyConnectionImpl implements FamilyConnection {
    @Autowired
    private HttpConnection connection;

    @Autowired
    private FamilyConnectionConfig fcc;


    @Override
    public Family getFamily(long id){

        String url = fcc.getFamilyUrl();
        CompletableFuture<String> fContent = connection.doGet(url+"/"+id);
        String content = "";
        try{content = fContent.get();}
        catch(Exception ex) {System.out.println("Exception caught: "+ex);}

        Family f = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            f = mapper.readValue(content, Family.class);
        }
        catch(JsonMappingException ex){System.out.println("JsonMappingException:"+ex);}
        catch (IOException ex){System.out.println("IOException: " + ex);}

        return f;
    }

    @Override
    public Family getStubFamily(long familyId){
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

    @Override
    public Family createFamily(Family family){
        return null;
    }
}
