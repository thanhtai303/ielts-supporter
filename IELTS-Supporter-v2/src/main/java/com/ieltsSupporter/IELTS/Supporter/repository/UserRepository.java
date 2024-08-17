package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ieltsSupporter.IELTS.Supporter.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
