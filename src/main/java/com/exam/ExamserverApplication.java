package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;


/*
 * 
 * 
 * Front End Coding 
 * /home/harshal/Marvellous/Spring/springbootproject/Angular/examportal
 * 
 * 
 * 
 * 
 * */

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");
//		
//		User user = new User();
//		user.setFirstName("Harshal");
//		user.setLastName("Thitame");
//		user.setUsername("admin");
//		user.setPassword(bCryptPasswordEncoder.encode("123"));
//		user.setEmail("hthitame@gmail.com");
//		user.setProfile("default.png");
//		
//		
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//		
//		Set<UserRole> userRolesSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		
//		userRolesSet.add(userRole);
//		
//		User user2 = this.userService.createUser(user, userRolesSet);
//		
//		System.out.println(user2.getUsername());
		
	}

}
