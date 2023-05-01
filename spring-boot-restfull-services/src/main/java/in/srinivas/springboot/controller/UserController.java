package in.srinivas.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.springboot.entity.User;
import in.srinivas.springboot.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User savedUser = userService.saveUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<User> getUser(@PathVariable Integer userId){
		
		User user = userService.getUserById(userId);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
		
	}
	
	@PutMapping("{userId}")
	public ResponseEntity<User> updateUserById(@PathVariable Integer userId, @RequestBody User user){
		User updateUser = userService.updateUser(user);
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
		
	}
	
	@DeleteMapping("{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User has been deleted", HttpStatus.OK);
		
	}

}
