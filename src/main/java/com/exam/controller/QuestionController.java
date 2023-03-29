package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RequestMapping("/question")
@CrossOrigin("*")
@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// add question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// update question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// get all question of any quiz
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
		Quiz quiz = new Quiz();
		Quiz quiz1 = this.quizService.getQuiz(qid);

		
		quiz.setQid(qid);
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsofQuiz(quiz);
		
		List<Question> list = new ArrayList<>(questionsOfQuiz);
		System.out.println("size : "+quiz1.getnumberOfQuestions());
		
		if (list.size() > Integer.parseInt(quiz1.getnumberOfQuestions())) {
			list = (list.subList(0, Integer.parseInt(quiz1.getnumberOfQuestions())));
		}
		System.out.println(list.size());
		Collections.shuffle(list);
		
		return ResponseEntity.ok(list);
//
//		Quiz quiz = this.quizService.getQuiz(qid);
//		Set<Question> questions = quiz.getQuestions();
//		List<Question> list = new ArrayList<>();
//		list.addAll(questions);
//		
//		System.out.println(questions.toString());
//		
//		if (questions.size() > Integer.parseInt(quiz.getnumberOfQuestions())) {
//			list = list.subList(0, Integer.parseInt(quiz.getnumberOfQuestions() + 1));
//		}
//		
////		list.forEach((q)->{
////			q.setanswer("");
////		});
//		Collections.shuffle(list);
//		return ResponseEntity.ok(list);

	}

	// get all question of any quiz
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfAdmin(@PathVariable("qid") Long qid) {
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		Set<Question> questionsofQuiz = this.questionService.getQuestionsofQuiz(quiz);
		ArrayList<Question> list = new ArrayList<>(questionsofQuiz);
		Collections.shuffle(list);
		return ResponseEntity.ok(questionsofQuiz);

	}

	// get single question
	@GetMapping("/{quesid}")
	public Question get(@PathVariable("quesid") Long quesid) {
		return this.questionService.getQuestion(quesid);
	}

	// delete question
	@DeleteMapping("/{quesid}")
	public void delete(@PathVariable("quesid") Long quesid) {
		this.questionService.deleteQuestion(quesid);
	}

	// eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
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
		

		return ResponseEntity.ok(questions);

	}

}
