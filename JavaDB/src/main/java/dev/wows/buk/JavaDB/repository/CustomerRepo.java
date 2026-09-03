package dev.wows.buk.JavaDB.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
