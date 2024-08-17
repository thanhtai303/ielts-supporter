package com.ieltsSupporter.IELTS.Supporter.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieltsSupporter.IELTS.Supporter.mapper.WritingMapper;
import com.ieltsSupporter.IELTS.Supporter.model.Writing;
import com.ieltsSupporter.IELTS.Supporter.repository.WritingRepository;

@Service
public class WritingService {

    @Autowired
    WritingRepository writingRepository;

    @Autowired
    private WritingMapper writingMapper;

    // Get all Writing Test from Database
    public List<Writing> getAllWritingTest() {
        return writingRepository.findAll();
    }

    // Get one Writing Test from Database by ID
    public Optional<Writing> getWritingTestByID(int wri_id) {
        return writingRepository.findById(wri_id);
    }

    // Add more Writing Test to Database
    // If the New-Writing-Test-ID has been taken, the result is null
    public Writing addNewWritingTest(Writing newWriting) {
        if (writingRepository.findById(newWriting.getWri_id()).isPresent()) {
            return null;
        }
        return writingRepository.save(newWriting);
    }

    // Update one Writing-Test-Information by ID
    // If the Writing-Test-ID has not found, the result will be null
    public Writing updateWritingTest(Writing newWriting, int wri_id) {
        Writing updatedWriting = writingRepository.findById(wri_id).get();
        if (updatedWriting != null && wri_id == newWriting.getWri_id()) {
            updatedWriting = writingMapper.toWriting(newWriting);
            writingRepository.save(updatedWriting);
            return updatedWriting;
        }
        return null;
    }

    // Delete Writing Test by ID
    // If the Writing Test is present the result will delete it from Database
    // If the Writing Test is not present the result will be false
    public boolean deleteWritingTest(int wri_id) {
        if (writingRepository.existsById(wri_id)) {
            writingRepository.deleteById(wri_id);
            return true;
        }
        return false;
    }
}
