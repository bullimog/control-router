package router.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import router.models.Person;
import java.util.List;


// Requires:
//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-data-rest</artifactId>
//</dependency>

//Just needs this file, and that's it....
// Produces Json with lots of metadata.

/*                              MongoCollection             httpPath */
@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRestRepository extends MongoRepository<Person, String> {

    List<Person> findByLast(@Param("name") String name);

}