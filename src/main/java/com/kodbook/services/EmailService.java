package com.kodbook.services;



public interface EmailService {
	String sendtextEMail(String email);

	String sendtextEMailSignup(String email,String username);
}
