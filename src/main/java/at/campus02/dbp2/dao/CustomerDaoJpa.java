package at.campus02.dbp2.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDaoJpa implements CustomerDao{

    private EntityManagerFactory factory;// required to create and manage the Entity Manager
    private EntityManager manager;

    public CustomerDaoJpa() {
        factory = Persistence.createEntityManagerFactory("nameOfJpaPersistenceUnit");
        manager = factory.createEntityManager();
    }

    @Override
    public void create(Customer customer) {
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
    }

    @Override
    public Customer read(String lastName) {
        return manager.find(Customer.class, lastName);
    }

    @Override
    public void update(Customer customer) {
        manager.getTransaction().begin();
        manager.merge(customer); // will search for the customer object based on the primary key and change it is cache and resubmit
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Customer customer) {
        manager.getTransaction().begin();
        //manager.remove(manager.merge(customer));
        Customer managed = manager.merge(customer);
        manager.remove(managed);
        manager.getTransaction().commit();
    }
}
