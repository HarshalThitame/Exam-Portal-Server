package com.exam.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.User;
import com.exam.model.exam.PasswordResetToken;
import com.exam.model.exam.SendMail;
import com.exam.repo.PasswordResetTokenRepository;
import com.exam.service.SendMailService;
import com.exam.service.UserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/forgot")
public class ForgotPassword {
    @Autowired
    private UserService userService;
    @Autowired 
    private PasswordResetTokenRepository tokenRepository;
    @Autowired 
    private SendMailService emailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @PostMapping("/")
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> abc,HttpServletRequest request) {
		// Lookup user in database by e-mail
		System.out.println(abc);
		
		String email = abc.get("email");
		
		Optional<User> optional = userService.findUserByEmail(email);
		if(!optional.isPresent())
		{
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("error");
		}
		else {
			User user = optional.get();
			
			PasswordResetToken token = new PasswordResetToken();
	        token.setToken(UUID.randomUUID().toString());
	        token.setUser(user);
	        token.setExpiryDate(30);
			
	        SendMail mail = new SendMail();
	        mail.setTo(email);
	        mail.setSubject("Password reset request");
	        Map<String, Object> model = new HashMap<>();
	        model.put("token", token);
	        model.put("user", user);
	        model.put("signature", "https://memorynotfound.com");
	        String url = request.getScheme() + "://" + request.getServerName() 
//	        + ":"+request.getServerPort() 
	        ;
	        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
	        mail.setModel(model);
	        mail.setMessage(url+"/reset-password/" + token.getToken());
	        
	   
	        	boolean sendEmail = emailService.sendEmail(mail);
	        	
	        	if(sendEmail)
	        	{
	        		tokenRepository.save(token);
	        		return ResponseEntity.ok(user);
	        		
	        	}else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("network error");
				}
				
			

		}
		
	}
    
    @CrossOrigin("*")
    @GetMapping("/{link}")
    public ResponseEntity<?> getUserByToken(@PathVariable("link") String token)
    {
    	
    	PasswordResetToken resetToken = this.tokenRepository.findByToken(token);
    	if(resetToken == null)
    	{
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Invalid token");
    	}
    	
    	return ResponseEntity.ok(resetToken);
    }
    
    @PutMapping("/")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordResetToken token)
    {
    	System.out.println(token);
    	User user = new User();
    	user = token.getUser();
    	
		user.setPassword(bCryptPasswordEncoder.encode(token.getUser().getPassword()));
		
		User user2 = userService.updateUser(token.getUser());

		if(user2 == null)
    	{
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Invalid user");
    	}
		
    	this.tokenRepository.deleteById(token.getId());

		
    	return	ResponseEntity.ok(user2);
    }
    
    
}
