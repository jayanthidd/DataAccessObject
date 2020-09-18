package at.campus02.dbp2.dao;

public class Application {
    public static void main(String[] args) {

        //Prepare the customer
        Customer customer1 = new Customer();
        customer1.setLastName("Fuchs");
        customer1.setFirstName("Fritz");
        customer1.setAge(30);

        // Data Access Object holen - from where?

        //In Memory Implementation
        //CustomerDao dao = new CustomerDaoInMemory();

        //JDBC Implementation
        CustomerDao dao = new CustomerDaoJdbc("jdbc:derby:database;create = true");

        //1. Create
        dao.create(customer1);
        log("Created customer1.");

        //2. Read
        Customer fromDB = dao.read(customer1.getLastName());
        log("Read customer from database : " + fromDB);

        //3. Update and read again
        customer1.setFirstName("Freddy");
        log("Kontrolle : " + dao.read(customer1.getLastName()));
        dao.update(customer1);
        fromDB = dao.read(customer1.getLastName());
        log("Updated customer1 in the database : " + fromDB);

        //4. Delete
        dao.delete(customer1);
        fromDB = dao.read(customer1.getLastName());
        log("Deleted customer1 from the database : " + fromDB);
    }

    public static void log(String msg){
        System.out.println("Application: -- " + msg);
    }
}
