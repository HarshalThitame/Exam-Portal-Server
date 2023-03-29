package com.exam.service;

import java.util.*;
import java.util.List;

import com.exam.model.User;
import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.UserResult;

public interface ResultService {

	public UserResult addResult(UserResult userResult);
	public UserResult updateResult(UserResult userResult);
	
	public List<UserResult> getAllUserResults();

	public void deleteResult(Long rid);
	
	public List<UserResult> getUserResultOfUser(User user);
	public List<UserResult> getUserResultOfQuiz(Quiz quiz);
	public List<UserResult> getUserResultOfCategory(String category);
	public List<UserResult> getUserResultOfQuiz(String quiz);
	
	public List<UserResult> getUSerResultSearchByUsername(String query);
	
	public List<UserResult> getResultBetweenDates(Date start ,Date end);
	
//	public List<Integer> countDistinctRackBy

	public List<UserResult> getUserResultOfQuizsByOrderByMarksGot(Quiz quiz);
	

}
