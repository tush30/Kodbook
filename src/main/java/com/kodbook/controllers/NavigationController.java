package com.kodbook.controllers;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}