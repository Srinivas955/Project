package in.srinivas.springboot.service;

import java.util.List;

import in.srinivas.springboot.entity.User;

public interface UserService {
	
	User saveUser(User user);
	User getUserById(Integer userId);
	List<User> getAllUsers();
	User updateUser(User user);
	void deleteUser(Integer userId);

}
