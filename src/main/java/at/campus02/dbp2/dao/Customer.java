package at.campus02.dbp2.dao;

public class Customer {

    private String lastName;
    private String firstName;
    private Integer age;

    // default constructor is enough for JPA

    public Customer() {
    }

    //a help constructor for cloning
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
