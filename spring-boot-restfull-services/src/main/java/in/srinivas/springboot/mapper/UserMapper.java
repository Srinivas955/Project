package in.srinivas.springboot.mapper;

import in.srinivas.springboot.dto.UserDto;
import in.srinivas.springboot.entity.User;

public class UserMapper {
	
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(
				user.getId(),
				user.getEmail(),
				user.getFirstName(),
				user.getLastName()				
				);
		return userDto;		
	}
	
	public static User mapToUser(UserDto dto) {
		
		User user = new User(
				dto.getId(),
				dto.getEmail(),
				dto.getFirstName(),
				dto.getLastName()
				);
		return user;
	}
	

}
