package in.mohit.app.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.customer.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);
}
