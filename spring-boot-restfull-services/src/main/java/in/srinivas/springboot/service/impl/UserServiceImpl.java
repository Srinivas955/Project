package in.srinivas.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.srinivas.springboot.dto.UserDto;
import in.srinivas.springboot.entity.User;
import in.srinivas.springboot.exception.ResourceNotFoundException;
import in.srinivas.springboot.exception.UserAlreadyExistsException;
import in.srinivas.springboot.mapper.AutoUserMapper;
import in.srinivas.springboot.repository.UserRepository;
import in.srinivas.springboot.service.UserService;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository uesrRepo;
	
	@Autowired
	private ModelMapper modelMappre;

	@Override
	public UserDto saveUser(UserDto userDto) {
		User newUser = uesrRepo.findByEmail(userDto.getEmail());
		if(newUser != null) {
			throw new UserAlreadyExistsException("user already eixsts");
		}
		//User user = userDto.toUser();
		//User user = UserMapper.mapToUser(userDto);
		//User user = modelMappre.map(userDto, User.class);
		
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		User savedUser = uesrRepo.save(user);
		
		//UserDto dto = user.toUserDto();
		//UserDto dto = UserMapper.mapToUserDto(user);
		//UserDto dto = modelMappre.map(savedUser, UserDto.class);
		
		UserDto dto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		return dto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = uesrRepo.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("user", "id", userId)
				);
		
		//User user = findById.get();
		
		//return UserMapper.mapToUserDto(user);
		//return modelMappre.map(user, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(user); 
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = uesrRepo.findAll();
		//List<UserDto> usersList = users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());		
		//List<UserDto> usersList = users.stream().map((user)->modelMappre.map(user, UserDto.class)).collect(Collectors.toList());
		
		List<UserDto> usersList = users.stream().map((user)->AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
		
		return usersList; 
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User existingUser = uesrRepo.findById(userDto.getId()).orElseThrow(
				()->new ResourceNotFoundException("User", "id", userDto.getId())
				);
		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setLastName(userDto.getLastName());
		existingUser.setEmail(userDto.getEmail());
		 User newUser = uesrRepo.save(existingUser);
		 //return newUser.toUserDto();
		 //return modelMappre.map(newUser, UserDto.class);
		 return AutoUserMapper.MAPPER.mapToUserDto(newUser);
	}

	@Override
	public void deleteUser(Integer userId) {
		uesrRepo.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User", "id", userId)
				);
		uesrRepo.deleteById(userId);
	}

	
}
