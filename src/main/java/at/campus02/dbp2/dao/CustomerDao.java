package at.campus02.dbp2.dao;

// to define CRUD operations for the customer table
public interface CustomerDao {

    void create(Customer customer);
    Customer read(String lastName);
    void update(Customer customer);
    void delete(Customer customer);

}
