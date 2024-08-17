package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ieltsSupporter.IELTS.Supporter.model.ReadingAnswer;

@Repository
public interface ReadingAnswerRepository extends JpaRepository<ReadingAnswer, Integer> {
}
