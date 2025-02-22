package in.mohit.app.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.order.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);
}
