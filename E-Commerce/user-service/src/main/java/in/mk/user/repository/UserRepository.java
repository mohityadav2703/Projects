package in.mk.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
