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
import com.kodbook.entities.Post;

@Controller
public class PostController {
@Autowired
PostService service;

@PostMapping("/addpost")
public String addPost(@RequestParam String caption,@RequestParam MultipartFile photo,Model model) {
	Post pp = new Post();
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
	
	return "redirect:/refreshlogin";
}
@GetMapping("/addlike")
public String addlike(@RequestParam Long idp,Model model) {
	Post post = service.getPost(idp);
	post.setLike_count(post.getLike_count()+1);
	service.updatePost(post);
	List<Post> list = service.listall();
	model.addAttribute("photolist",list);
	
	return "home";
}
@PostMapping("/addcomment")
public String addComment(@RequestParam Long idp,@RequestParam String comment, Model model) {
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
	
	return "home";
}

}
