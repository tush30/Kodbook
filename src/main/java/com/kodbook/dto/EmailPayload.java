package com.kodbook.dto;

public class EmailPayload {

	private String recepientEmail;
	private String emailMessage;

	public String getRecepientEmail() {
		return recepientEmail;
	}

	public void setRecepientEmail(String recepientEmail) {
		this.recepientEmail = recepientEmail;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

}
