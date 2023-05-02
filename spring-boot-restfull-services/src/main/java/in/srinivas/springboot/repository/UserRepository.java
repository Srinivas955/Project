package in.srinivas.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.srinivas.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
