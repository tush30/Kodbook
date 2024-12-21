package com.kodbook.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;

@Controller
public class PostController {
@Autowired
PostService service;
@Autowired
UserService service2;


@PostMapping("/addpost")
public String addPost(@RequestParam String caption,@RequestParam MultipartFile photo,Model model,HttpSession session) {
	//for like
		// this section from dp image because when hit like button dp c'nt access so making acceptable when redirect to home page
		String username1 =(String) session.getAttribute("username");
		//fetch the user object using username
		User user =service2.getProfileUsername(username1);
	
		Post pp = new Post();
		//updating post object
		pp.setUser(user);
		
		pp.setCaption(caption);
		
	try {
		if(!photo.isEmpty()) {
			pp.setPhoto(photo.getBytes());//save photo
		}
	}
	catch (IOException e) {
		e.printStackTrace();
		model.addAttribute("error", "Error uploading photo. Please try again.");
        return "errorpage"; // Replace with your error page
		}
	
	service.createpost(pp);// save the post

//	List<Post> list = service.listall();
//	model.addAttribute("photolist",list);
	
	//updating user object
	List<Post> posts = user.getPosts();
	if(posts == null) {
		posts = new ArrayList<Post>();
	}
	posts.add(pp);
	user.setPosts(posts);
	service2.updateProfile(user);
	
	
	
	return "redirect:/refreshlogin";
}
@PostMapping("/addlike")
public String addlike(@RequestParam Long idp,Model model,HttpSession session) {
	Post post = service.getPost(idp);
	post.setLike_count(post.getLike_count()+1);
	service.updatePost(post);
	List<Post> list = service.listall();
	
	model.addAttribute("photolist",list);
	
	//for like
	// this section from dp image because when hit like button dp c'nt access so making acceptable when redirect to home page
	String username1 =(String) session.getAttribute("username");
	//fetch the user object using username
	User user =service2.getProfileUsername(username1);
	model.addAttribute("user", user);
	
	return "redirect:/refreshlogin";
}
@PostMapping("/addcomment")
public String addComment(@RequestParam Long idp,@RequestParam String comment, Model model,HttpSession session) {
	//getting id ans storing in post object
	
	System.out.println(comment);
	Post post = service.getPost(idp);
	// getting comment from list type instantce
    List<String>  comments = post.getComments();
    //if comment list is emapty
	if(comments==null) {
		//then create new list object for comments
		comments=new ArrayList<String>();
	}
	//then add the comment from user/client
	comments.add(comment);
	// after that set the list comments 
	post.setComments(comments);
	//at last upadte the post object which is saves the commets
	service.updatePost(post);
	
	List<Post> list = service.listall();
	model.addAttribute("photolist",list);
	
	//for comment
		// this section from dp image because when hit like button dp c'nt access so making acceptable when redirect to home page
		String username1 =(String) session.getAttribute("username");
		//fetch the user object using username
		User user =service2.getProfileUsername(username1);
		model.addAttribute("user", user);
	
	return "redirect:/refreshlogin";
}



}
