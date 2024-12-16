package com.kodbook.services;

import java.util.List;


import com.kodbook.entities.User;

public interface UserService {

	void addUser(User user);

	boolean validateUser(String username, String email);

	boolean validateUserEmailPass(String email, String password);

	boolean validateEmail(String email);

	void validatepassword(String email,String password );

	

}
