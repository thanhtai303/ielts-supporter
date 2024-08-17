package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ieltsSupporter.IELTS.Supporter.model.Listening;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningRepository extends JpaRepository<Listening, Integer> {
}
