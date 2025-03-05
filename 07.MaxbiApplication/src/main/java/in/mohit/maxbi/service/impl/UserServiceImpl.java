package in.mohit.maxbi.service.impl;

import java.util.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.mohit.maxbi.dto.UserDTO;
import in.mohit.maxbi.entity.UserEntity;
import in.mohit.maxbi.repo.UserRepository;
import in.mohit.maxbi.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private final UserRepository userRepo;
	private final BCryptPasswordEncoder pwdEncoder;
	
	public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder pwdEncoder) {
		this.userRepo=userRepo;
		this.pwdEncoder=pwdEncoder;
	}

	@Override
	public boolean registerUser(UserDTO userDTO) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userDTO, user);
		String pwdEncoded = pwdEncoder.encode(user.getPassword());
		user.setPassword(pwdEncoded);
		UserEntity userData = userRepo.save(user);
		return userData.getId()!=null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user=userRepo.findByEmail(email);
		return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
	}

	@Override
	public UserEntity getUserProfile(Long userId) {
	    return userRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
	}

	@Override
	public UserEntity updateUserProfile(Long userId, UserDTO userDTO) {
	    UserEntity user = userRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
	    
	    BeanUtils.copyProperties(userDTO, user);
	    return userRepo.save(user);
	}

}
