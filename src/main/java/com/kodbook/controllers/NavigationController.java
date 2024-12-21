package com.kodbook.controllers;

import java.security.PublicKey;
import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class NavigationController {
	@Autowired
	UserService service;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/signuppage")
	 public String signup() {
		return "signuppage";
	}
	@GetMapping("/forgotpass")
	public String resetpass() {
		return "resetpassword";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	@GetMapping("/confirmpass")
	public String conpass() {
		return "confirmpassword";
	}
	
	@GetMapping("/updateProfileuser")
	public String editProfile() {
		return "/editprofile";
	}
	@GetMapping("/visitprofile")
	public String visituserProfile(@RequestParam String username,Model model,HttpSession session) {
		//getting user object from user using useranme from front end in home page
		String logedUser = (String) session.getAttribute("username");
		User loged = service.getProfileUsername(logedUser);
		model.addAttribute("logedUser", loged);
		
		User user =service.getProfileUsername(username);
		model.addAttribute("user", user);
		
		//getting all user post from who posted all posts getting from mapping where mapping set in during addpost of both user and post object
		List<Post> mypost = user.getPosts();
		model.addAttribute("mypost", mypost);
		return "showProfile";
	}
}