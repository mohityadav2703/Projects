package in.mohit.maxbi.service;

import in.mohit.maxbi.dto.UserDTO;
import in.mohit.maxbi.entity.UserEntity;

public interface UserService {
	
	boolean registerUser(UserDTO userDTO);
	UserEntity getUserProfile(Long userId);
	UserEntity updateUserProfile(Long userId, UserDTO userDTO);
}
