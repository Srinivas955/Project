package in.srinivas.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.springboot.dto.UserDto;
import in.srinivas.springboot.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
		UserDto savedUser = userService.saveUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		
		UserDto user = userService.getUserById(userId);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers(){
		List<UserDto> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
		
	}
	
	@PutMapping("{userId}")
	public ResponseEntity<UserDto> updateUserById(@PathVariable Integer userId, @RequestBody @Valid UserDto user){
		UserDto updateUser = userService.updateUser(user);
		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
		
	}
	
	@DeleteMapping("{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User has been deleted", HttpStatus.OK);
		
	}
	
	/*
	 * @ExceptionHandler(ResourceNotFoundException.class) public
	 * ResponseEntity<ErrorResponse>
	 * handleResourceNotFoundException(ResourceNotFoundException exception,
	 * WebRequest webRequest){ ErrorResponse errorResponse = new ErrorResponse(
	 * LocalDateTime.now(), exception.getMessage(),
	 * webRequest.getDescription(false), "user not found");
	 * 
	 * return new ResponseEntity<ErrorResponse>(errorResponse,
	 * HttpStatus.NOT_FOUND); }
	 */
	

}
