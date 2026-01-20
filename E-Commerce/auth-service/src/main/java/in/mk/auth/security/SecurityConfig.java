package in.mk.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain filterChain(HttpSecurity http) {
		http.csrf(AbstractHttpConfigurer :: disable)
		.authorizeHttpRequests(auth-> auth
				.requestMatchers(
					    "/auth/**",
					    "/actuator/**",
					    "/swagger-ui/**",
					    "/v3/api-docs/**",
					    "/swagger-resources/**",
					    "/webjars/**"
					).permitAll()
				.anyRequest().authenticated());
		return http.build();
	}
}
