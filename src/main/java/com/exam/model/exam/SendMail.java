package com.exam.model.exam;

import java.util.Map;

public class SendMail {

	private String to;
	private String subject;
	private String message;
    private Map<String, Object> model;


	public SendMail() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	

	public SendMail(String to, String subject, String message, Map<String, Object> model) {
		super();
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.model = model;
	}





	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getModel() {
		return model;
	}


	public void setModel(Map<String, Object> model) {
		this.model = model;
	}


	@Override
	public String toString() {
		return "EmailRequest [to=" + to + ", subject=" + subject + ", message=" + message + "]";
	}


}
