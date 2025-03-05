package in.mohit.app.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.app.notification.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
