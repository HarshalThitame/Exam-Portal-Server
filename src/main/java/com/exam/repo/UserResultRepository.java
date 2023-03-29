package com.exam.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.User;
import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Long>{
	
	public List<UserResult> findAllByOrderByRidDesc();
	public List<UserResult> findByUserROrderByRidDesc(User user);
	public List<UserResult> findByQuizsOrderByRidDesc(Quiz quiz);
	public List<UserResult> findByQuizs_Category_TitleContainingOrderByRidDesc(String category);

	public List<UserResult> findByQuizsTitleContainingOrderByRidDesc(String quiz);

	public List<UserResult> findByQuizs_TitleContainingOrUserR_FirstNameContainingOrderByRidDesc(String query,String query1);
	
	public List<UserResult> findByCreatedDateBetween(Date start,Date end);
	
	public List<UserResult> findByQuizsOrderByMarksGotDesc(Quiz quiz);

}
