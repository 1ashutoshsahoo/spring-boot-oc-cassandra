package in.ashu.oc.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import in.ashu.oc.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

	
	public List<Customer> findByFirstname(String firstname);

	@AllowFiltering
	public List<Customer> findByAgeGreaterThan(int age);

}
