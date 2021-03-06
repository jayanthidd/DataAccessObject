package at.campus02.dbp2.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    private String lastName;
    @Basic // this is the default annotation
    private String firstName;
    private Integer age;

    // default constructor is required for JPA
    public Customer() {
    }

    //a help constructor for cloning so that the actual reference of the object is not automatically changed
    public Customer(Customer toClone){
        lastName = toClone.getLastName();
        firstName = toClone.getFirstName();
        age = toClone.age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " : " + age ;
    }
}
