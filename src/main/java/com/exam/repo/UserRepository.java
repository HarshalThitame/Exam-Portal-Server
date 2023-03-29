package com.exam.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.model.User;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	public User findByUsernameAndPhone(String username,String phone);
	public Optional findByEmail(String email);
//	public Optional findByResetToken(String resetToken);
	
	@Query("SELECT u FROM User u JOIN u.userRoles ur JOIN ur.role r WHERE r.roleName = 'NORMAL'")
	public List<User> findAllNormalUser();

	@Modifying
    @Query("UPDATE User u SET u.enabled = :enabled WHERE u.id = :id")
    public int updateUserEnabled(@Param("id") Long id, @Param("enabled") boolean enabled);

}
