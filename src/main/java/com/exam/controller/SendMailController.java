package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.SendMail;
import com.exam.service.SendMailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mail")
public class SendMailController {

	@Autowired
	private SendMailService sendMailService;
	
	@PostMapping("/")
	public ResponseEntity<?> sendEmail(@RequestBody SendMail request) {
		
		System.out.println(request);
		
		SendMail sendMail = new SendMail();
		sendMail.setTo(request.getTo());
		sendMail.setSubject(request.getSubject());
		sendMail.setMessage(request.getMessage());
		
		boolean result= this.sendMailService.sendEmail(sendMail);
		
		return ResponseEntity.ok(result);
	}
}
