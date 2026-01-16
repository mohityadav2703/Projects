package in.mk.user.service;

import java.util.List;

import in.mk.user.dtos.UserResponse;

public interface UserService {

	UserResponse getProfile();

	List<UserResponse> getAllUsers();
}
