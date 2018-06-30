package in.ashu.oc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.ashu.oc.model.Customer;
import in.ashu.oc.repository.CustomerRepository;

@SpringBootApplication
public class SpringBootOcApplication implements CommandLineRunner {

	@Autowired
    private CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootOcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		clearData();
		saveData();
		lookup();		
	}
	
	public void clearData(){
		customerRepository.deleteAll();
	}
	
	public void saveData(){
		System.out.println("=================== Save Customers to Cassandra : Start ===================");
		Customer cust_1 = new Customer(1, "Peter", "Smith", 20);
        Customer cust_2 = new Customer(2, "Mary", "Taylor", 25);
        Customer cust_3 = new Customer(3, "Peter", "Brown", 30);
        Customer cust_4 = new Customer(4, "Lauren", "Taylor", 20);
        Customer cust_5 = new Customer(5, "Lauren", "Flores", 45);
        Customer cust_6 = new Customer(6, "Peter", "Williams", 20);
 
        // Save customers to cassandra
        customerRepository.save(cust_1);
        customerRepository.save(cust_2);
        customerRepository.save(cust_3);
        customerRepository.save(cust_4);
        customerRepository.save(cust_5);
        customerRepository.save(cust_6);
        System.out.println("=================== Save Customers to Cassandra : Successful ===================");
	}
	
	public void lookup(){
		System.out.println("=================== Lookup Customers from Cassandra by Firstname : Start ===================");
		List<Customer> peters = customerRepository.findByFirstname("Peter");
		peters.forEach(System.out::println);
		System.out.println("=================== Lookup Customers from Cassandra by Firstname : Successful ===================");
		
		System.out.println("=================== Lookup Customers from Cassandra by Age :Start ===================");
		List<Customer> custsAgeGreaterThan25 = customerRepository.findByAgeGreaterThan(25);
		custsAgeGreaterThan25.forEach(System.out::println);
		System.out.println("=================== Lookup Customers from Cassandra by Age : Successful ===================");
	}
}
