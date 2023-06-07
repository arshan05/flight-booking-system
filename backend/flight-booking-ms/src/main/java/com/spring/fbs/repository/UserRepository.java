package com.spring.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.fbs.model.User;

public interface UserRepository extends MongoRepository<User, String>{

}
