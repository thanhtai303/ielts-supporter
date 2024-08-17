package com.ieltsSupporter.IELTS.Supporter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ieltsSupporter.IELTS.Supporter.mapper.ReadingMapper;
import com.ieltsSupporter.IELTS.Supporter.model.Reading;
import com.ieltsSupporter.IELTS.Supporter.repository.ReadingRepository;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private ReadingMapper readingMapper;

    // Get all Reading Test from Database
    public List<Reading> getAllReadingTest() {
        return readingRepository.findAll();
    }

    // Get one Reading Test from Database by ID
    public Optional<Reading> getReadingTestByID(int rea_id) {
        return readingRepository.findById(rea_id);
    }

    // Add more Reading Test to Database
    // If the New-Reading-Test-ID has been taken, the result is null
    public Reading addNewReadingTest(Reading newReading) {
        if (readingRepository.findById(newReading.getRea_id()).isPresent()) {
            return null;
        }
        return readingRepository.save(newReading);
    }

    // Update one Reading-Test-Information by ID
    // If the Reading-Test-ID has not found, the result will be null
    public Reading updateReadingTest(Reading newReading, int rea_id) {
        Reading updatedReading = readingRepository.findById(rea_id).get();
        if (updatedReading != null && rea_id == newReading.getRea_id()) {
            updatedReading = readingMapper.toReading(newReading);
            readingRepository.save(updatedReading);
            return updatedReading;
        }
        return null;
    }

    // Delete Reading Test by ID
    // If the Reading Test is present the result will delete it from Database
    // If the Reading Test is not present the result will be false
    public boolean deleteReadingTest(int rea_id) {
        if (readingRepository.existsById(rea_id)) {
            readingRepository.deleteById(rea_id);
            return true;
        }
        return false;
    }
}
