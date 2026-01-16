package in.mk.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.auth.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);
}
