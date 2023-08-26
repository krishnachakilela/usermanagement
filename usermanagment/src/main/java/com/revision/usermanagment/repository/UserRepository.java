package com.revision.usermanagment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.revision.usermanagment.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	
	Optional<User> findByEmailId(String emailId);
	Optional<User> findByUserNameAndPassword(String userName,String password);
}
