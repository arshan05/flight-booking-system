package com.fbs.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fbs.auth.model.User;

@Repository
public interface UsersRepository extends MongoRepository<User, String>{

	 User findByUsername(String username);

	boolean existsByUsername(String username);
}
