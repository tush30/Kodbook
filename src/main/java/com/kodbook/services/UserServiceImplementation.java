package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	UserRepository repo;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		repo.save(user);
	}

	@Override
	public boolean validateUser(String username,String email) {
		// TODO Auto-generated method stub
		User user1 =repo.findByUsername(username);
		User user2 =repo.findByEmail(email);
		if(user1!=null || user2!=null) {
			
			return true;
		}
		
		else {
		return false;
		}
	}

	

	//both pass and email will check
	
	public boolean validateUserEmailPass(String email, String password) {
	    User checkUser = repo.findByEmail(email); // Assuming you're retrieving by email

	    if (checkUser == null) {
	        // Handle case when the user is not found
	        System.out.println("User not found with email: " + email);
	        return false; // Or throw an exception if appropriate
	    }

	    // Compare the passwords
	    return checkUser.getPassword().equals(password);
	}

	//email only check
	public boolean validateEmail(String email) {
		User checkUser =repo.findByEmail(email);
		if(checkUser!=null) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public void validatepassword(String email, String password) {
		// TODO Auto-generated method stub
		User checkUser = repo.findByEmail(email);
		if(checkUser != null) {
		checkUser.setPassword(password);
		repo.save(checkUser);
		}
		else {
			System.out.println("email not found");
		}
	}

	@Override
	public void updateProfile(User user) {
		// TODO Auto-generated method stub
		repo.save(user);
	}

	@Override
	public String getuserName(String email) {
		
		User user =repo.findByEmail(email);
		return  user.getUsername();
	}

	@Override
	public User getProfileUsername(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}
	
public User getuserDp(Long id) {
		
		
		return  repo.findById(id).get(); 
	}
	

	

	
	
	
}
