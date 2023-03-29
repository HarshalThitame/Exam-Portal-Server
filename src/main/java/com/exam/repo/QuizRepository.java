package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(boolean b);
	public List<Quiz> findByCategoryAndActive(Category c,boolean b);
	
	public List<Quiz> findByTitleContaining(String query);
	
	public List<Quiz> findByTitleContainingAndActive(String query,boolean b);
	
	
	
}
