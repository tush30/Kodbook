package com.kodbook.services;

import java.util.List;

import com.kodbook.entities.*;
;

public interface PostService {
	void createpost(Post pp);
	List<Post> listall();
	Post getPost(Long idp);
	void updatePost(Post post);
}
