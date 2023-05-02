package in.srinivas.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.srinivas.springboot.dto.UserDto;
import in.srinivas.springboot.entity.User;

@Mapper
public interface AutoUserMapper {
	
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
	User mapToUser(UserDto userDto);
	
	UserDto mapToUserDto(User user);

}
