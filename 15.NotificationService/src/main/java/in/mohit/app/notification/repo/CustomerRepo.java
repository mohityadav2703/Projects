package in.mohit.app.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.notification.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);
}
