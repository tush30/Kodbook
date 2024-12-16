package com.kodbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.EmailService;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	PostService service1;
	
	@PostMapping("/signup")
	public String  signUp(@ModelAttribute User user,Model model) {
		String username = user.getUsername();
		String email = user.getEmail();
		//user exist ?
		boolean status = service.validateUser(username,email);
		if(status == false) {
			service.addUser(user);
			emailService.sendtextEMailSignup(email,username);
			
			return "index";
		}
		else {
			model.addAttribute("msg","USer already exist");
			return "index";
		}
		
	}
	
	@PostMapping("/loginpage")
	public  String openLogin(@RequestParam String email,@RequestParam String password,Model model) {
		boolean status =service.validateUserEmailPass(email,password);
		if(status==true) {
			
			List<Post> list = service1.listall();
			model.addAttribute("photolist",list);
			return "home";
		}
		else {
			model.addAttribute("msg","Incorrect Email and Password");
		return "index";
		}
	}
	
	@PostMapping("/validatemail")
	public String findEmail(@RequestParam String email,Model model) {
		boolean status = service.validateEmail(email);
		if(status == true) {
			return "confirmpassword";
		}else {
			model.addAttribute("msg","Email is not found");
			return "resetpassword";
		}
	}
	
	@PostMapping("/updatepassword")
	public String updatepass(@RequestParam String email,@RequestParam String password) {
			
		 	service.validatepassword(email,password);
		 	
		
			return "index";
		
		
	}
	
	@GetMapping("/refreshlogin")//this method using for avioding duplicates when add new post
	public String refreshlogin(Model model) {
		List<Post> list = service1.listall();
		model.addAttribute("photolist",list);
		return "home";
		
	}
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/text")
	public String sendTextEmail(@RequestParam String email,
			Model model) {
		boolean status = service.validateEmail(email);
		
		if(status == true) {
			emailService.sendtextEMail(email);
			return "checkEmail";
		}else {
			model.addAttribute("msg","Email is not found");
			return "resetpassword";
		}
		
	}
	
}
