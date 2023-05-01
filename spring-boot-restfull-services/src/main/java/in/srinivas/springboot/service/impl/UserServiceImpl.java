package in.srinivas.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.srinivas.springboot.entity.User;
import in.srinivas.springboot.repository.UserRepository;
import in.srinivas.springboot.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository uesrRepo;

	@Override
	public User saveUser(User user) {
		return uesrRepo.save(user);
	}

	@Override
	public User getUserById(Integer userId) {
		
		Optional<User> findById = uesrRepo.findById(userId);
		
		return findById.get();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = uesrRepo.findAll();	
		return users;
	}

	@Override
	public User updateUser(User user) {
		User existingUser = uesrRepo.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		 User newUser = uesrRepo.save(existingUser);
		 return newUser;
	}

	@Override
	public void deleteUser(Integer userId) {
		uesrRepo.deleteById(userId);
	}

	
}
