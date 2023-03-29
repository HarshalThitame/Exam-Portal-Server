package com.exam.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.User;
import com.exam.model.exam.Category;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.UserResult;
import com.exam.service.QuestionService;
import com.exam.service.ResultService;
import com.exam.service.UserService;
import com.exam.service.impl.UserDetailsServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/result")
public class UserResultController {
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/")
	public ResponseEntity<?> addResult(@RequestBody List<Question> questions,Principal principal){
		
		User user = (User) this.userDetailsService.loadUserByUsername(principal.getName());
		
		
		System.out.println(questions);
		int marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		

		for (Question q : questions) {
//			System.out.println(q.getGivenAnswer());
			Question question = this.questionService.get(q.getQuesid());
			if (question.getanswer().equals(q.getGivenAnswer())) {
				correctAnswers++;

				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;
			}
			if (q.getGivenAnswer() != null ) {
				attempted++;
			}
		}
		;
		Map<String,Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		
		UserResult userResult = new UserResult();
		userResult.setAttempted(attempted);
		userResult.setCorrectAns(correctAnswers);
		userResult.setMarksGot(marksGot);
		userResult.setQuizs(questions.get(0).getQuiz());
		userResult.setUserR(user);
		

		return ResponseEntity.ok(this.resultService.addResult(userResult));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllResult()
	{
		return ResponseEntity.ok(this.resultService.getAllUserResults());
	}
	
	@GetMapping("/byuser/{userid}")
	public ResponseEntity<?> getResultByUser(@PathVariable("userid") Long id)
	{
		User user = new User();
		user.setId(id);
		
		return ResponseEntity.ok(this.resultService.getUserResultOfUser(user));
	}
	
	@GetMapping("/byquiz/{qid}")
	public ResponseEntity<?> getResultByQuiz(@PathVariable("qid") Long qid)
	{
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		
		return ResponseEntity.ok(this.resultService.getUserResultOfQuiz(quiz));
	}
	
	@GetMapping("/cat/{title}")
	public ResponseEntity<?> getResultByCategry(@PathVariable("title") String title)
	{
//		Category category = new Category();
		
		return ResponseEntity.ok(this.resultService.getUserResultOfCategory(title));
	}
	
	@GetMapping("/quiz/{title}")
	public ResponseEntity<?> getResultByQuiz(@PathVariable("title") String title)
	{
//		Category category = new Category();
		
		return ResponseEntity.ok(this.resultService.getUserResultOfQuiz(title));
	}
	
	@GetMapping("/rank/{qid}")
	public ResponseEntity<?> getRankByQuiz(@PathVariable("qid") Long qid)
	{
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		
		return ResponseEntity.ok(this.resultService.getUserResultOfQuizsByOrderByMarksGot(quiz));
	}
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> getUSerResultSearchByUsername(@PathVariable("query") String query)
	{
		
		
		return ResponseEntity.ok(this.resultService.getUSerResultSearchByUsername(query));
	}
	
	
	@PostMapping("/search")
	public ResponseEntity<?> getUSerResultBetweendates(@RequestBody Map<String, Date> dates)
	{
		Date start = dates.get("start");
		Date end = dates.get("end");
		return ResponseEntity.ok(this.resultService.getResultBetweenDates(start, end));
	}
	
}
