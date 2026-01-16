package in.mk.auth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.mk.auth.dtos.UserCreateRequest;

@FeignClient(name = "user-service")
public interface UserClient {

	@PostMapping("/internal/users")
	void createUser(@RequestBody UserCreateRequest request);
}
