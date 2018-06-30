package in.ashu.oc.model;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

	@AllowFiltering
	public List<Customer> findByFirstname(String firstname);

	@AllowFiltering
	public List<Customer> findByAgeGreaterThan(int age);

}
