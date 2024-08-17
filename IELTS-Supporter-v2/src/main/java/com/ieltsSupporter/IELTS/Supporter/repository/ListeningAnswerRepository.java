package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ieltsSupporter.IELTS.Supporter.model.ListeningAnswer;

@Repository
public interface ListeningAnswerRepository extends JpaRepository<ListeningAnswer, Integer> {
}
