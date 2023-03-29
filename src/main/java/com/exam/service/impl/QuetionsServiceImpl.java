package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.*;

import com.exam.model.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.service.QuestionService;

@Service
public class QuetionsServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Question Question(com.exam.model.exam.Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionsofQuiz(Quiz quiz) {
		
		return new HashSet<>(this.questionRepository.findByQuiz(quiz));
	}

	@Override
	public void deleteQuestion(Long quesid) {
		
		Question question = new Question();
		question.setQuesid(quesid);
		this.questionRepository.delete(question);
	}

	@Override
	public com.exam.model.exam.Question get(Long quesid) {
		// TODO Auto-generated method stub
		return this.questionRepository.getOne(quesid);
	}

}
