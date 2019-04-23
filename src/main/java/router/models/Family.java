package router.models;

import java.util.HashSet;

public class Family {
    private final long id;
    private final String address1;
    private final String address2;
    private HashSet<Person> people;

    public Family (long id, String address1, String address2, HashSet<Person> people){
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
        this.people = people;
    }

    public long getId(){return id;}
    public String getAddress1(){return address1;}
    public String getAddress2(){return address2;}
    public HashSet<Person> getPeople(){return people;}
}
