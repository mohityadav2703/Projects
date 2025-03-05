package in.mohit.maxbi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mohit.maxbi.dto.UserDTO;
import in.mohit.maxbi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register") 
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
		return null;
	}

	@PostMapping("/login") 
	public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO){
		return null;
	}

	@GetMapping("/{userId}") 
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId){
		return null;
	}

	@PutMapping("/{userId}") 
	public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody UserDTO userDTO){
		return null;
	}

}
