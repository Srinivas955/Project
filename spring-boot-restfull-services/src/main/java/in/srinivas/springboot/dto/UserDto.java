package in.srinivas.springboot.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import in.srinivas.springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	
	private Integer id;
	
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	
	public User toUser() {
		
		return User.builder()
				.id(id)
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.build();
	}
	
	
	
	

	
	
	
	
	
	
	
}
