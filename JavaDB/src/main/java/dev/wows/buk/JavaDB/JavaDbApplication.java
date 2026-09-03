package dev.wows.buk.JavaDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.wows.buk.JavaDB.repository.CustomerRepo;

@SpringBootApplication
public class JavaDbApplication {

	public static void main(String[] args) {
		// SpringApplication.run(JavaDbApplication.class, args);

		temporaryCustomerTest();
	}

	public static void temporaryCustomerTest() {

		try {

			CustomerRepo customerRepo = new CustomerRepo();

			System.out.println("--- connect to the db ---");
			customerRepo.connectToDatabase();
		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
		}
	}
}
