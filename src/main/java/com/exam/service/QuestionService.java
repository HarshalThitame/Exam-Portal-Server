package com.exam.service;

import java.util.Set;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);
	public Question Question(Question question);
	public Set<Question> getQuestions();
	public Question getQuestion(Long  questionId);
	public Set<Question> getQuestionsofQuiz(Quiz quiz);
	public void deleteQuestion(Long quesid);
	public Question get(Long quesid);
}
