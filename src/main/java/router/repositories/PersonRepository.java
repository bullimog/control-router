package router.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import router.models.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByFirst(String firstName);
    public List<Person> findByLast(String lastName);
    public void deleteByFirst(String firstName);

}