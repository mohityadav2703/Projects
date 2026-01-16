package in.mk.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mk.user.dtos.UserCreateRequest;
import in.mk.user.entity.User;
import in.mk.user.repository.UserRepository;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	public void create(@RequestBody UserCreateRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole("ROLE_"+request.getRole());
		repository.save(user);
	}
	
}
