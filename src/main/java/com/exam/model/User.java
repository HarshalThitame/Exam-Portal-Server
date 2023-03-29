package com.exam.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.model.exam.UserResult;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String phone;
	private boolean enabled = true;
	private String profile;


	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	private Date createdDate;

	@PrePersist
	private void onCreate() {
		createdDate = new Date();
	}

	// user has many roles
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userR")
	@JsonIgnore
	private List<UserResult> userResults = new ArrayList<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String password, String firstName, String lastName, String email,
			String phone, boolean enabled, String profile, Date createdDate, Set<UserRole> userRoles,
			List<UserResult> userResults) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
		this.createdDate = createdDate;
		this.userRoles = userRoles;
		this.userResults = userResults;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<UserResult> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserResult> userResults) {
		this.userResults = userResults;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", enabled=" + enabled
				+ ", profile=" + profile + ", createdDate=" + createdDate + ", userRoles=" + userRoles
				+ ", userResults=" + userResults + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();

		this.userRoles.forEach(userRole -> {
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
