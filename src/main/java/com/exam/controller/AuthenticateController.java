package com.exam.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.UserService;
import com.exam.service.config.JwtUtils;
import com.exam.service.config.RsaKeyProperties;
import com.exam.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	

	
	@Autowired
	private JwtUtils jwtUtils;
	

	
	//generate token
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		System.out.println("UserName : "+jwtRequest.getUsername()+" Password "+jwtRequest.getPassword());
		try {
//			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
				
			} catch (DisabledException e) {
			
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("User diabled");
//				throw new Exception("User disabled");
				
			   
			}
			catch (BadCredentialsException e) {
				throw new Exception("Bad credentials "+e.getMessage());
			}
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("user not found");
		}
		
		
		//authenticate user
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username,String password) throws Exception {

		
		
	}
	
	
	//return the details of current login user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
	}
}
