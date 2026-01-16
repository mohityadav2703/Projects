package in.mk.auth.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.mk.auth.entity.AuthUser;
import in.mk.auth.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final AuthUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AuthUser user = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().replace("ROLE_", ""))
				.build();
	}

}
