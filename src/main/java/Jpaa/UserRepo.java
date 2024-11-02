package Jpaa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	public List<User> findByName(String name);
	public List<User> findByNameStartingWith(String prefix);
}
