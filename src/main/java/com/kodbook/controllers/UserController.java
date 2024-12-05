package com.kodbook.controllers;

import org.hibernate.tool.schema.internal.StandardUserDefinedTypeExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entities.User;
import com.kodbook.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public String  signUp(@ModelAttribute User user,Model model) {
		String username = user.getUsername();
		String email = user.getEmail();
		//user exist ?
		boolean status = service.validateUser(username,email);
		if(status == false) {
			service.addUser(user);
			return "index";
		}
		else {
			model.addAttribute("msg","USer already exist");
			return "index";
		}
		
	}
	
	@PostMapping("/loginpage")
	public String openLogin(@RequestParam String email,@RequestParam String password,Model model) {
		boolean status =service.validateUserEmailPass(email,password);
		if(status==true) {
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
	
}
