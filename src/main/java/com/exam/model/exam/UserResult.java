package com.exam.model.exam;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.exam.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class UserResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rid;
	
	private int attempted;
	private int correctAns;
	private double marksGot;
	
	@Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
	
	@PrePersist
	private void onCreate() {
		createdDate = new Date();
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	private User userR;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quizs;

	public UserResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public int getAttempted() {
		return attempted;
	}

	public void setAttempted(int attempted) {
		this.attempted = attempted;
	}

	public int getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}

	public double getMarksGot() {
		return marksGot;
	}

	public void setMarksGot(double marksGot) {
		this.marksGot = marksGot;
	}

	public User getUserR() {
		return userR;
	}

	public void setUserR(User userR) {
		this.userR = userR;
	}

	

	public Quiz getQuizs() {
		return quizs;
	}

	public void setQuizs(Quiz quizs) {
		this.quizs = quizs;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "UserResult [rid=" + rid + ", attempted=" + attempted + ", correctAns=" + correctAns + ", marksGot="
				+ marksGot + ", createdDate=" + createdDate + ", userR=" + userR + ", quizs=" + quizs + "]";
	}

	



}
