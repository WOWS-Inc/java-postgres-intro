package dev.wows.buk.JavaDB.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wows.buk.JavaDB.model.Customer;
import dev.wows.buk.JavaDB.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() throws SQLException {

        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getOne(@PathVariable long id) throws SQLException {

        Optional<Customer> optCustomer = customerService.getOne(id);

        if (optCustomer.isPresent())
            return ResponseEntity.ok(optCustomer.get());

        return ResponseEntity.notFound().build();        
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) throws SQLException {

        Optional<Customer> optCustomer = customerService.add(customer);

        if (optCustomer.isPresent())
            return ResponseEntity.ok(optCustomer.get());

        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> update(@PathVariable long id, @RequestBody Customer customer) throws SQLException {

        Optional<Customer> optCustomer = customerService.update(id, customer);

        if (optCustomer.isPresent())
            return ResponseEntity.ok(optCustomer.get());

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Customer> delete(@PathVariable long id) throws SQLException {

        Optional<Customer> optCustomer = customerService.delete(id);

        if (optCustomer.isPresent())
            return ResponseEntity.ok(optCustomer.get());

        return ResponseEntity.notFound().build();
    }
}
