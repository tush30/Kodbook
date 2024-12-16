package com.kodbook.controllers;

import java.security.PublicKey;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NavigationController {
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
		public String logOut() {
			return "index";
	}
	@GetMapping("/confirmpass")
	public String conpass() {
		return "confirmpassword";
	}
	@GetMapping("/myprofile")
	public String myProfile() {
		return "myProfile";
	}
	@GetMapping("/editprofile")
	public String editProfile() {
		return "editprofile";
	}
	
}