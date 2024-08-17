package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ieltsSupporter.IELTS.Supporter.model.Writing;
import org.springframework.stereotype.Repository;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Integer> {
}
