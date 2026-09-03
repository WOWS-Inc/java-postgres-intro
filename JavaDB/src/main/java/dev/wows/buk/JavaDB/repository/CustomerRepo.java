package dev.wows.buk.JavaDB.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.wows.buk.JavaDB.db.DatabaseConnection;
import dev.wows.buk.JavaDB.model.Customer;

@Repository
public class CustomerRepo {

    private final Connection connection;

    public CustomerRepo() throws SQLException {

        DatabaseConnection dbc = new DatabaseConnection();
        connection = dbc.getConnection();
    }

    public void connectToDatabase() throws SQLException {

        PreparedStatement ps = connection.prepareStatement("""
            SELECT *
            FROM Customers
        """);

        ResultSet res = ps.executeQuery();

        List<Customer> customers = new ArrayList<>();
        while(res.next()) {

            long id = res.getLong("id");
            String name = res.getString("name");
            String address = res.getString("address");
            String email = res.getString("email");
            String phone = res.getString("phone");

            customers.add(new Customer(id, name, address, email, phone));
        }

        System.out.println(customers);
    }

    public List<Customer> getAll() throws SQLException {

        PreparedStatement ps = connection.prepareStatement("""
            SELECT *
            FROM Customers
        """);

        ResultSet res = ps.executeQuery();

        List<Customer> customers = new ArrayList<>();
        while(res.next())
            customers.add(new Customer(res));

        return customers;
    }

    public Optional<Customer> getOne(long id) throws SQLException { 

        PreparedStatement ps = connection.prepareStatement("""
            SELECT *
            FROM Customers
            WHERE id = ?
        """);

        ps.setLong(1, id);

        ResultSet res = ps.executeQuery();

        if (res.next())
            return Optional.of(new Customer(res));

        return Optional.empty();
    }

    public Optional<Customer> add(Customer customer) throws SQLException {

        final String SQL = """
            INSERT INTO Customers(name, address, email, phone)
            VALUES
                (?, ?, ?, ?)
        """;

        PreparedStatement ps = connection.prepareStatement(
            SQL, Statement.RETURN_GENERATED_KEYS
        );

        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getEmail());
        ps.setString(4, customer.getPhone());

        int rows = ps.executeUpdate();
        
        if (rows > 0)
            try (ResultSet res = ps.getGeneratedKeys()) {
                
                if (res.next()) {

                    customer.setId(res.getLong("id"));
                    return Optional.of(customer);
                }
            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }

        return Optional.empty();
    }

    public Optional<Customer> update(long id, Customer customer) throws SQLException {

        final String SQL = """
            UPDATE customers
            SET 
                name = ?,
                address = ?,
                email = ?,
                phone = ?
            WHERE id = ?
        """;

        PreparedStatement ps = connection.prepareStatement(SQL);

        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getEmail());
        ps.setString(4, customer.getPhone());

        ps.setLong(5, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            return getOne(id);

        return Optional.empty();
    }

    public Optional<Customer> delete(long id) throws SQLException {

        final Optional<Customer> target = getOne(id);
        if (target.isEmpty())
            return target;

        final String SQL = """
            DELETE FROM customers
            WHERE id = ?
        """;

        PreparedStatement ps = connection.prepareStatement(SQL);

        ps.setLong(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            return target;

        return Optional.empty();
    }
}
