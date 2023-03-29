package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.exam.model.exam.SendMail;
import com.exam.service.SendMailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailServiceImpl implements SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public boolean sendEmail(SendMail sendMail) {

		try {
//			SimpleMailMessage mailMessage = new SimpleMailMessage();

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

			helper.setFrom(sender);
			helper.setTo(sendMail.getTo());
			helper.setText(sendMail.getMessage(),true);
			helper.setSubject(sendMail.getSubject());

			javaMailSender.send(mimeMessage);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
