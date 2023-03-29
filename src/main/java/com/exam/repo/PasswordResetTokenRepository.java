package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.User;
import com.exam.model.exam.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    void deleteById(Long id);
   

}
