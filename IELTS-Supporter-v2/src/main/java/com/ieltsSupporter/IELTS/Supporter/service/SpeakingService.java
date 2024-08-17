package com.ieltsSupporter.IELTS.Supporter.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieltsSupporter.IELTS.Supporter.mapper.SpeakingMapper;
import com.ieltsSupporter.IELTS.Supporter.model.Speaking;
import com.ieltsSupporter.IELTS.Supporter.repository.SpeakingRepository;

@Service
public class SpeakingService {

    @Autowired
    SpeakingRepository speakingRepository;

    @Autowired
    private SpeakingMapper speakingMapper;

    // Get all Speaking Test from Database
    public List<Speaking> getAllSpeakingTest() {
        return speakingRepository.findAll();
    }

    // Get one Speaking Test from Database by ID
    public Optional<Speaking> getSpeakingTestByID(int spe_id) {
        return speakingRepository.findById(spe_id);
    }

    // Add more Speaking Test to Database
    // If the New-Speaking-Test-ID has been taken, the result is null
    public Speaking addNewSpeakingTest(Speaking newSpeaking) {
        if (speakingRepository.findById(newSpeaking.getSpe_id()).isPresent()) {
            return null;
        }
        return speakingRepository.save(newSpeaking);
    }

    // Update one Speaking-Test-Information by ID
    // If the Speaking-Test-ID has not found, the result will be null
    public Speaking updateSpeakingTest(Speaking newSpeaking, int spe_id) {
        Speaking updatedSpeaking = speakingRepository.findById(spe_id).get();
        if (updatedSpeaking != null && spe_id == newSpeaking.getSpe_id()) {
            updatedSpeaking = speakingMapper.toSpeaking(newSpeaking);
            speakingRepository.save(updatedSpeaking);
            return updatedSpeaking;
        }
        return null;
    }

    // Delete Speaking Test by ID
    // If the Speaking Test is present the result will delete it from Database
    // If the Speaking Test is not present the result will be false
    public boolean deleteSpeakingTest(int spe_id) {
        if (speakingRepository.existsById(spe_id)) {
            speakingRepository.deleteById(spe_id);
            return true;
        }
        return false;
    }
}
