package in.mohit.app.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.mohit.app.customer.service.impl.MyUserDetailService;
import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(myUserDetailService);
		authProvider.setPasswordEncoder(pwdEncoder());
		return authProvider;
	}
	
	
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http) {
		http.authorizeHttpRequests(req->
			req.requestMatchers("/register","/login","pwd/reset","/pwd/forgot/**").permitAll()
			.anyRequest().authenticated());
		
		return http.csrf().disable().build();
	}
	

}
