package router.models;

public class Person {
    private final long id;
    private final String first;
    private final String last;
    private final int age;

    public Person(long id, String first, String last, int age){
        this.id = id;
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public long getId() {
        return id;
    }
    public String getFirst(){return first;}
    public String getLast(){return last;}
    public int getAge(){return age;}
}
