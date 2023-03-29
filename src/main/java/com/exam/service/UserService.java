package com.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;

import jakarta.transaction.Transactional;

public interface UserService {

	//creating user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	public User getUser(String username);
	
	public User getUserbyId(Long id);
	
	public User updateUser(User user);
	
	//delete user by id
	public void deleteUser(Long userId);
	
	public List<User> getAllUsers();
	
	public List<User> getAllNormalUsers();

    public Optional<User> findUserByEmail(String email);
//    public Optional<User> findUserByResetToken(String resetToken);
    
    public int updateUserEnabled(Long id,boolean enabled);
	
}

