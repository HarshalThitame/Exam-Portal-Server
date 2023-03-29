package com.exam.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = this.userRepository.findByUsername(user.getUsername());
		
		if(local!=null)
		{
			System.out.println("User already exists !!!");
			throw new Exception("User already present !!!");
		}
		else
		{
			//create user
			
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

	//getting user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	
	//getting user by userid
		@Override
		public User getUserbyId(Long id) {
			// TODO Auto-generated method stub
			return this.userRepository.findById(id).get();
		}

	@Override
	public void deleteUser(Long userId) {

		this.userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(User user) {
		
//		for(UserRole ur:userRoles)
//		{
//			roleRepository.save(ur.getRole());
//		}
//		
//		user.getUserRoles().addAll(userRoles);
//		
		User user2 = this.userRepository.save(user);
		return user2;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	@Override
	public Optional findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllNormalUsers() {
		return this.userRepository.findAllNormalUser();
	}

	@Override
	public int updateUserEnabled(Long id, boolean enabled) {
		return this.userRepository.updateUserEnabled(id, enabled);
	}

//	@Override
//	public Optional findUserByResetToken(String resetToken) {
//		return userRepository.findByResetToken(resetToken);
//	}

}
