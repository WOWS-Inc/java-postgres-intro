package dev.wows.buk.JavaDB.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.wows.buk.JavaDB.model.Customer;
import dev.wows.buk.JavaDB.repository.CustomerRepo;

@Service
public class CustomerService {

    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;
    }

    public List<Customer> getAll() throws SQLException {

        return customerRepo.getAll();
    }

    public Optional<Customer> getOne(long id) throws SQLException { 

        return customerRepo.getOne(id);
    }
    public Optional<Customer> add(Customer customer) throws SQLException {

        return customerRepo.add(customer);
    }

    public Optional<Customer> update(long id, Customer customer) throws SQLException {

        return customerRepo.update(id, customer);
    }
    
    public Optional<Customer> delete(long id) throws SQLException {

        return customerRepo.delete(id);
    }
}
