package in.mohit.app.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.order.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
