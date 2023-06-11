package com.fbs.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fbs.auth.model.User;
import com.fbs.auth.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userOp = repository.findByUsername(username);
		if (userOp == null) {
			throw new UsernameNotFoundException(
					"User Not Found with username: " + username);
		}
		else {
			return getUser(username, userOp);
		}
	}

	private UserDetails getUser(String username, User user) {
		 
		return UserDetailsImpl.getUser(user);
	}

}
