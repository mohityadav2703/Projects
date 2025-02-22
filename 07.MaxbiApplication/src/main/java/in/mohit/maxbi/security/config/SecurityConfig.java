package in.mohit.maxbi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.mohit.maxbi.service.impl.UserServiceImpl;
import lombok.SneakyThrows;

@Configuration
public class SecurityConfig {
	
	private final UserServiceImpl userService;
	
	public SecurityConfig(UserServiceImpl userService) {
		this.userService=userService;
	}
	
	
	/**
	 * this method is used to encrypt the password
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * this method is used to load user data from database
	 * @return
	 */
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(pwdEncoder());
		authProvider.setUserDetailsService(userService);
		return authProvider;
	}
	
	/**
	 * this method is used to validate user credentials
	 * @param config
	 * @return
	 */
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}

	/**
	 * this method is used to authorize the request
	 * @param http
	 * @return
	 */
	@SuppressWarnings("removal")
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http) {
		http.authorizeHttpRequests(req-> 
			req.requestMatchers("/user/register","/user/login").permitAll()
				.anyRequest().authenticated());
		return http.csrf().disable().build();
	}
}
