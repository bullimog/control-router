package router.models;

import java.util.HashSet;

public class Family {
    private long id = 0;
    private  String address1="";
    private  String address2="";
    private HashSet<Person> persons =  new HashSet<Person>();

    public Family (long id, String address1, String address2, HashSet<Person> persons){
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
        this.persons = persons;
    }

    public Family(){}

    public void setId (long id){this.id = id;}
    public void setAddress1 (String address1){this.address1 = address1;}
    public void setAddress2 (String address2){this.address2 = address2;}
    public void setPersons (HashSet persons){this.persons = persons;}

    public long getId(){return id;}
    public String getAddress1(){return address1;}
    public String getAddress2(){return address2;}
    public HashSet<Person> getPersons(){return persons;}
}
