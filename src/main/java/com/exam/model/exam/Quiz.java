package com.exam.model.exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qid;
	private String title;
	private String description;
	private String maxMarks;
	private String numberOfQuestions;
	private boolean active = false;

	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();
	
	@OneToMany(mappedBy = "quizs",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserResult> userResults = new ArrayList<>();
	
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getnumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setnumberOfQuestions(String numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public List<UserResult> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserResult> userResults) {
		this.userResults = userResults;
	}

	@Override
	public String toString() {
		return "Quiz [qid=" + qid + ", title=" + title + ", description=" + description + ", maxMarks=" + maxMarks
				+ ", numberOfQuestions=" + numberOfQuestions + ", active=" + active + ", category=" + category
				+ ", questions=" + questions + ", userResults=" + userResults + "]";
	}

	
}
