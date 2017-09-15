package com.xcompany.xproject.auth.server.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByLogin(String login);

	User findByEmail(String login);


	// void deleteById(Integer );

	@Modifying
	@Query("delete from User where id = ?1")
	void delete(Integer userId);

	//@Modifying
	//void delete(User user);
}