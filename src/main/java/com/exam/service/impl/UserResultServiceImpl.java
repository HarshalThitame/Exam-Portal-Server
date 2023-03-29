package com.exam.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.UserResult;
import com.exam.repo.UserResultRepository;
import com.exam.service.ResultService;


@Service
public class UserResultServiceImpl implements ResultService{

	@Autowired
	private UserResultRepository userResultRepository;
	
	@Override
	public UserResult addResult(UserResult userResult) {
		// TODO Auto-generated method stub
		return this.userResultRepository.save(userResult);
	}

	@Override
	public UserResult updateResult(UserResult userResult) {
		// TODO Auto-generated method stub
		return this.userResultRepository.save(userResult);
	}

	@Override
	public void deleteResult(Long rid) {
		// TODO Auto-generated method stub
		
		this.userResultRepository.deleteById(rid);
	}

	@Override
	public List<UserResult> getAllUserResults() {
		// TODO Auto-generated method stub
		return this.userResultRepository.findAllByOrderByRidDesc();
	}

	@Override
	public List<UserResult> getUserResultOfUser(User user) {
		// TODO Auto-generated method stub
		return this.userResultRepository.findByUserROrderByRidDesc(user);
	}

	@Override
	public List<UserResult> getUserResultOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.userResultRepository.findByQuizsOrderByRidDesc(quiz);
	}
	

	@Override
	public List<UserResult> getUserResultOfCategory(String category) {
		// TODO Auto-generated method stub
		return this.userResultRepository.findByQuizs_Category_TitleContainingOrderByRidDesc(category);
	}
	
	@Override
	public List<UserResult> getUserResultOfQuiz(String quiz) {
		// TODO Auto-generated method stub
		return this.userResultRepository.findByQuizsTitleContainingOrderByRidDesc(quiz);
	}


	@Override
	public List<UserResult> getUSerResultSearchByUsername(String query) {
		// TODO Auto-generated method stub
		return this.userResultRepository.findByQuizs_TitleContainingOrUserR_FirstNameContainingOrderByRidDesc(query,query);
	}

	@Override
	public List<UserResult> getResultBetweenDates(Date start, Date end) {
		// TODO Auto-generated method stub
		
		return this.getResultBetweenDates(start, end);
	}

	@Override
	public List<UserResult> getUserResultOfQuizsByOrderByMarksGot(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.userResultRepository.findByQuizsOrderByMarksGotDesc(quiz);
	}

}
