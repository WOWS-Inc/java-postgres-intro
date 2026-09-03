package dev.wows.buk.JavaDB;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.wows.buk.JavaDB.model.Customer;
import dev.wows.buk.JavaDB.repository.CustomerRepo;

@SpringBootApplication
public class JavaDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaDbApplication.class, args);

		// temporaryCustomerTest();
	}

	public static void temporaryCustomerTest() {

		try {

			CustomerRepo customerRepo = new CustomerRepo();

			System.out.println("--- connect to the db ---");
			customerRepo.connectToDatabase();

			System.out.println("\n\n--- get all customers ---");
			List<Customer> customers = customerRepo.getAll();
			System.out.println(customers);

			System.out.println("\n\n--- get customer by id 1 ---");
			Optional<Customer> validCustomer = customerRepo.getOne(1);
			if (validCustomer.isPresent())
				System.out.println(validCustomer.get());
			else
				System.out.println("customer is missing");

			System.out.println("\n\n--- get customer by id 100 ---");
			Optional<Customer> missingCustomer = customerRepo.getOne(100);
			if (missingCustomer.isPresent())
				System.out.println(missingCustomer.get());
			else
				System.out.println("customer is missing");

			System.out.println("\n\n--- insert new customer ---");
			Customer customer = new Customer("Guybrush", "Monkey Insland", "guybrush@wows.dev", "12345");
			Optional<Customer> newCustomer = customerRepo.add(customer);
			if (newCustomer.isPresent())
				System.out.println(newCustomer.get());
			else
				System.out.println("customer is missing");

			System.out.println("\n\n--- delete customer id 11 ---");
			Optional<Customer> deletedCustomer = customerRepo.delete(11);
			if (deletedCustomer.isPresent())
				System.out.println(deletedCustomer.get());
			else
				System.out.println("customer is missing");

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
		}
	}
}
