package com.exam.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import com.nimbusds.jose.shaded.gson.JsonObject;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {

		// encoding password with berypt password encoder
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);
		if (user.getProfile() == null) {
			user.setProfile("default.png");
		}
		return this.userService.createUser(user, roles);
	}

	@PutMapping("/")
	public User updateUser(@RequestBody User user) throws Exception {

		// encoding password with berypt password encoder
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

//		Set<UserRole> roles = new HashSet<>();
//		Role role = new Role();
//		role.setRoleId(45L);
//		role.setRoleName("NORMAL");
//		
//		UserRole userRole = new UserRole();
//		userRole.setUser(user);
//		userRole.setRole(role);

//		roles.add(userRole);
		if (user.getProfile() == null) {
			user.setProfile("default.png");
		}
		return this.userService.updateUser(user);
	}
	
	@PutMapping("/en")
	public int updateEnableOrDisabled(@RequestBody User user)
	{
		System.out.println(user.isEnabled());
		return this.userService.updateUserEnabled(user.getId(), user.isEnabled());
	}

	// get user by userid
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {

		return this.userService.getUser(username);
	}

	// get user by userid
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {

		return ResponseEntity.ok(this.userService.getUserbyId(id));
	}

	// delete user by userid
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {

		this.userService.deleteUser(userId);

	}

	// get all users
	@GetMapping("/all")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//get all normal user
	@GetMapping("/normal")
	public ResponseEntity<?> getAllNormalUser() {
		return ResponseEntity.ok(this.userService.getAllNormalUsers());
	}

	

}
