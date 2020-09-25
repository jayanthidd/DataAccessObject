package at.campus02.dbp2.dao;

import java.sql.*;

public class CustomerDaoJdbc implements CustomerDao{
    // What do we need to have a connection?
    // - A Driver with Derby embedded(in our case)
    // - the path to the database (jdbc url)
    private Connection connection;

    public CustomerDaoJdbc(String jdbcUrl) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");// we are loading the Derby driver
            connection = DriverManager.getConnection(jdbcUrl);
            ensureTable();
        } catch (SQLException | ClassNotFoundException throwables) {// we are catching checked exceptions and
            throw new IllegalStateException();// we are throwing them as a runtime exception
        }
    }

    private void ensureTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "create table CUSTOMER(" +
                            "lastname varchar(50) primary key, " +
                            "firstname varchar(50), " +
                            "age int)"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            //In case th table already exists, a special sql state exists with an exception - this can be ignored and we can throw
            //other exceptions further
            if("X0Y32".equalsIgnoreCase(e.getSQLState()))
                return;
            else
                throw e;
        }

    }

    @Override
    public void create(Customer customer) {
        try {
            PreparedStatement insert = connection.prepareStatement("insert into CUSTOMER values(?,?,?)");
            insert.setString(1, customer.getLastName());
            insert.setString(2, customer.getFirstName());
            insert.setInt(3, customer.getAge());
            insert.executeUpdate();
        } catch (SQLException throwables) {
            throw new IllegalStateException("Could not insert customer.", throwables);// we are giving a message and throwing the exception as a runtime exception
        }
    }

    @Override
    public Customer read(String lastName) {
        PreparedStatement query = null;
        try {
            query = connection.prepareStatement("select * from CUSTOMER where lastname = ?");
            query.setString(1, lastName);
            ResultSet result = query.executeQuery();
            if(result.next()){
                Customer fromDB = new Customer();
                fromDB.setLastName(result.getString(1));
                fromDB.setFirstName(result.getString(2));
                fromDB.setAge(result.getInt(3));
                return fromDB;
            }

        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement update = null;
        try {
            update = connection.prepareStatement("update CUSTOMER set firstname = ?, age = ? where lastname =?");
            update.setString(1, customer.getFirstName());
            update.setInt(2, customer.getAge());
            update.setString(3, customer.getLastName());
            update.executeUpdate();
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            PreparedStatement delete = connection.prepareStatement("delete from CUSTOMER where lastname = ?");
            delete.setString(1, customer.getLastName());
            delete.executeUpdate();
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }
}
