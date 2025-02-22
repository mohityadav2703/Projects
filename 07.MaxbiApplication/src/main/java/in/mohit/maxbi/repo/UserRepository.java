package in.mohit.maxbi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mohit.maxbi.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	Optional<UserEntity> findById(Long id);
}
