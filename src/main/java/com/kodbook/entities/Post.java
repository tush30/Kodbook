package com.kodbook.entities;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idp;
	private String caption;
	private int like_count;
	private List<String> comments;
	
	@ManyToOne
	private User user;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte [] photo;


	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Post(Long idp, String caption, int like_count, List<String> comments,User user, byte[] photo) {
		super();
		this.idp = idp;
		this.caption = caption;
		this.like_count = like_count;
		this.comments= comments;
		this.user = user;
		this.photo = photo;
	}



	
	public Long getIdp() {
		return idp;
	}



	public void setIdp(Long idp) {
		this.idp = idp;
	}



	public String getCaption() {
		return caption;
	}



	public void setCaption(String caption) {
		this.caption = caption;
	}



	public int getLike_count() {
		return like_count;
	}



	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}



	public List<String> getComments() {
		return comments;
	}



	public void setComments(List<String> comments) {
		this.comments = comments;
	}


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public String getPhotoBase64() {
		if(photo==null) {
			return null;
		}
		else {
			return Base64.getEncoder().encodeToString(photo);
		}
	}



	@Override
	public String toString() {
		return "Post [idp=" + idp + ", caption=" + caption + ", like_count=" + like_count + ", comments=" + comments
				+ ", user=" + user + ", photo=" + Arrays.toString(photo) + "]";
	}



	



	

	
}
