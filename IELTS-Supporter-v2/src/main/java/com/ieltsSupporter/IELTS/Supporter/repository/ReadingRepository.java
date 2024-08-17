package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ieltsSupporter.IELTS.Supporter.model.Reading;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Integer> {
}
