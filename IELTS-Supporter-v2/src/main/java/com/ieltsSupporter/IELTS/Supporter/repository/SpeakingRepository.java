package com.ieltsSupporter.IELTS.Supporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ieltsSupporter.IELTS.Supporter.model.Speaking;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakingRepository extends JpaRepository<Speaking, Integer> {
}
