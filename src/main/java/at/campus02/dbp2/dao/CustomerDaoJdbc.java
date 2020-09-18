package at.campus02.dbp2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerDaoJdbc implements CustomerDao{
    // What do we need to have a connection?
    // - A Driver with Derby embedded(in our case)
    // - the path to the database (jdbc url)
    private Connection connection;

    public CustomerDaoJdbc(String jdbcUrl) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");// we are loading the Derby driver
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException | ClassNotFoundException throwables) {// we are catching checked exceptions and
            throw new IllegalStateException();// we are throwing them as a runtime exception
        }
    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public Customer read(String lastName) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
