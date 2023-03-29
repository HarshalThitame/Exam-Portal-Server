package com.exam.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;


@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// add quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {

		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	// update quizz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {

		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	//get all quiz
	@GetMapping("/")
	public ResponseEntity<?> getAllQuizs() {
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	
	//get single quiz
	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	//delete quiz
	@DeleteMapping("/{qid}")
	public void Delete(@PathVariable("qid") Long qid)
	{
		this.quizService.deleteQuiz(qid);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesofCategory(@PathVariable("cid") Long cid)
	{
		Category category = new Category();
		category.setCid(cid);
		
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	//get active quizzes
		@GetMapping("/active")
		public List<Quiz> getActiveQuizzes(){
			return this.quizService.getActiveQuizzes();
		}
		
		//get active quizzes of category
		@GetMapping("/category/active/{cid}")
		public List<Quiz> getActiveQuiz(@PathVariable("cid") Long cid){
			Category category = new Category();
			category.setCid(cid);
			return this.quizService.getActiveQuizzesOfCategory(category);
		}
		
		@GetMapping("/search/{query}")
		public ResponseEntity<?> search(@PathVariable("query") String query) {
			System.out.println(query);
			
			
			List<Quiz> quizs = this.quizService.searchQuiz(query);
			
			return ResponseEntity.ok(quizs);
		}
		
		@GetMapping("/active/{query}")
		public ResponseEntity<?> searchActive(@PathVariable("query") String query) {
			System.out.println(query);
			
			
			List<Quiz> quizs = this.quizService.searchActiveQuizzes(query);
			
			return ResponseEntity.ok(quizs);
		}
		
		
}
