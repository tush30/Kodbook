package com.kodbook.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.EmailService;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;

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
	public  String openLogin(@RequestParam String email,@RequestParam String password,Model model,HttpSession session) {
		
		boolean status =service.validateUserEmailPass(email,password);
		
		if(status==true) {
			//feching usaer name by email
			String username = service.getuserName(email);
			

			//creatring seesion object
			session.setAttribute("username", username);
			model.addAttribute("session", session);
			//fechoing dp in home page accening object useing user name by session 
			User user =service.getProfileUsername(username);
			if (user != null) {
		        model.addAttribute("user", user);
		    } else {
		        model.addAttribute("user", new User()); // Prevent null
		    }
			//accening post from postService
			List<Post> list = service1.listall();
			model.addAttribute("photolist",list);
			return "home";
		}
		else {
			model.addAttribute("msg","Incorrect Email and Password Or user not found");
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
	public String refreshlogin(Model model,HttpSession session) {
		List<Post> list = service1.listall();
		model.addAttribute("photolist",list);
		
		//for after adding post redirecting home
				// this section from dp image because when add post button dp c'nt access so making acceptable when redirect to home page
				String username1 =(String) session.getAttribute("username");
				//fetch the user object using username
				User user =service.getProfileUsername(username1);
				model.addAttribute("user", user);
				
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
	
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob,@RequestParam String gender,
			@RequestParam String city,@RequestParam String bio,@RequestParam String college,
			@RequestParam String linkedIn,@RequestParam String github,@RequestParam MultipartFile dp,Model model,HttpSession session) {
		
			String username1 =(String) session.getAttribute("username");
			//fetch the user object using username
			User user =service.getProfileUsername(username1);
			
			//update and save the user object
			user.setDob(dob);
			user.setGender(gender);
			user.setCity(city);
			user.setBio(bio);
			user.setCollege(college);
			user.setLinkedIn(linkedIn);
			user.setGithub(github);
			
			try {
			
					user.setDp(dp.getBytes());//save photo
				
			}
			catch (IOException e) {
				e.printStackTrace();
				
		        
			}
			model.addAttribute("user", user);
			service.updateProfile(user);
			return "myProfile";
	}
	
	@GetMapping("/myprofile")
	public String myrofile(Model model,HttpSession session) {
		String username1 =(String) session.getAttribute("username");
		//fetch the user object using username
		User user =service.getProfileUsername(username1);
		model.addAttribute("user", user);
		//accesing indivual user which is looged through mapping
		List<Post> post = user.getPosts();
		model.addAttribute("userphotolist",post);
		return "myProfile";
	}
	
}
