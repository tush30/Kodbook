package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.repositories.PostRepository;
import com.kodbook.entities.*;

@Service
public class PostServiceImplementation implements PostService{
@Autowired
PostRepository repo;
public void createpost(Post pp) {
	repo.save(pp);
	
}
@Override
public List<Post> listall() {
	// TODO Auto-generated method stub
	return repo.findAll();
}

@Override
public Post getPost(Long idp) {
	// TODO Auto-generated method stub
	return repo.findById(idp).get();
}
@Override
public void updatePost(Post post) {
	// TODO Auto-generated method stub
	repo.save(post);
}
}
