package at.campus02.dbp2.dao;

import java.util.HashMap;
import java.util.Map;

public class CustomerDaoInMemory implements CustomerDao{
    private final Map<String, Customer> cache = new HashMap<>();// the reference cannot be changed but the contents can be changed

    @Override
    public void create(Customer customer) {
        cache.put(customer.getLastName(), new Customer(customer));
    }

    @Override
    public Customer read(String lastName) {
        //We need to do this so it is not referencing the same object all the time.  We are now returning the close and not the original.
        // so when a change is made, the change will be made to the close and not in the map.  The change will be updated in the map,
        // only when we call the update method
        Customer fromCache = cache.get(lastName);
        if (fromCache == null)
            return null;
        else
            return new Customer(fromCache);
        //same as above
        // return fromCache == null?null : new Customer(fromCache);
    }

    @Override
    public void update(Customer customer) {
        Customer fromCache = cache.get(customer.getLastName());
        if(fromCache==null)
            throw new IllegalStateException("Customer does not exist in the database!");
        fromCache.setFirstName(customer.getFirstName());
        fromCache.setAge(customer.getAge());
    }

    @Override
    public void delete(Customer customer) {
        if(!cache.containsKey(customer.getLastName()))
            throw new IllegalStateException("Customer does not exist in the database!");
        cache.remove(customer.getLastName());
    }
}
