package in.srinivas.springboot.service;

import java.util.List;

import in.srinivas.springboot.dto.UserDto;

public interface UserService {
	
	UserDto saveUser(UserDto user);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	UserDto updateUser(UserDto user);
	void deleteUser(Integer userId);

}
