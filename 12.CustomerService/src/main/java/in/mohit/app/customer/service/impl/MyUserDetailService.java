package in.mohit.app.customer.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.mohit.app.customer.entity.Customer;
import in.mohit.app.customer.repo.CustomerRepo;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer custDatails = customerRepo.findByEmail(email);
		return new User(custDatails.getEmail(), custDatails.getPassword(), Collections.emptyList());
	}

}
