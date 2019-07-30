package router.models;
import org.springframework.data.annotation.Id;

public class Person {
    @Id
    private  long id;
    private  String first;
    private  String last;
    private  int age;

    public Person(long id, String first, String last, int age){
        this.id = id;
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public Person(){}

    public long getId() {
        return id;
    }
    public String getFirst(){return first;}
    public String getLast(){return last;}
    public int getAge(){return age;}

    public void setId(long id) {this.id = id;}
    public void setFirst(String first) {this.first = first;}
    public void setLast(String last) {this.last = last;}
    public void setAge(int age) {this.age = age;}
}
