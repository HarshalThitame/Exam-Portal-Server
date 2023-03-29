package com.exam.model.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cid;
	private String title;
	private String description;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quiz> quizzes = new LinkedHashSet<>();
	

	public Category() {
		super();
	}

	public Category(Long cid, String title, String description) {
		super();
		this.cid = cid;
		this.title = title;
		this.description = description;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", title=" + title + ", description=" + description + "]";
	}

}
