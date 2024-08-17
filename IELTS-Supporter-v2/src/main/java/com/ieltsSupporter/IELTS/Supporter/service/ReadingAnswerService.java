package com.ieltsSupporter.IELTS.Supporter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieltsSupporter.IELTS.Supporter.mapper.ReadingAnswerMapper;
import com.ieltsSupporter.IELTS.Supporter.model.ReadingAnswer;
import com.ieltsSupporter.IELTS.Supporter.repository.ReadingAnswerRepository;

@Service
public class ReadingAnswerService {

    @Autowired
    ReadingAnswerRepository readingAnswerRepository;

    @Autowired
    ReadingAnswerMapper readingAnswerMapper;

    // Query all Reading Answer Package from Database
    public List<ReadingAnswer> queryAllReadingAnswer() {
        return readingAnswerRepository.findAll();
    }

    // Query Query oneReading Answer Package by ID
    public Optional<ReadingAnswer> queryReadingAnswerByID(int rea_ans_id) {
        return readingAnswerRepository.findById(rea_ans_id);
    }

    // Add more Reading Answer Package to Database
    // If the New-Listening-Answer-Package-ID has been taken, the result is null
    public ReadingAnswer addNewReadingAnswer(ReadingAnswer newReadingAnswer) {
        if (readingAnswerRepository.findById(newReadingAnswer.getRea_ans_id()).isPresent()
                || newReadingAnswer.getRea_ans_id() != newReadingAnswer.getRea_id()) {
            return null;
        }
        return readingAnswerRepository.save(newReadingAnswer);
    }

    // Calculate the overall score
    // Return include overall score, all correct answers and status of each answer
    public HashMap<String, HashMap<String, String>> calculateReadingScore(ReadingAnswer newReadingAnswer) {
        Optional<ReadingAnswer> foundAnsers = readingAnswerRepository.findById(newReadingAnswer.getRea_ans_id());
        if (foundAnsers.isEmpty()) {
            return null;
        }
        ReadingAnswer answer = foundAnsers.get();
        String[] checkAnswer = newReadingAnswer.allAnswer();
        String[] correctAnswer = answer.allAnswer();
        HashMap<String, String> outputCheckAnswer = new HashMap<>();
        HashMap<String, String> outputScore = new HashMap<>();
        HashMap<String, String> outputCorrectAnswer = new HashMap<>();
        HashMap<String, String> outputRate = new HashMap<>();
        long count = 0;
        double standardScore = 9.0 / 40.0;
        for (int i = 0; i < 40; i++) {
            String index = Integer.toString(i + 1);
            if (correctAnswer[i].compareToIgnoreCase(checkAnswer[i]) == 0) {
                count++;
                outputCheckAnswer.put("client_answer_" + index, "Correct");
            } else {
                outputCheckAnswer.put("client_answer_" + index, "Incorrect");
            }
            outputCorrectAnswer.put("lis_answer_" + index, correctAnswer[i]);
        }
        long score = Math.round(standardScore * count);
        outputScore.put("score", Long.toString(score));
        outputRate.put("rate", Long.toString(count) + "/40");
        HashMap<String, HashMap<String, String>> returnEntity = new HashMap<>();
        returnEntity.put("feedback", outputCheckAnswer);
        returnEntity.put("grading", outputScore);
        returnEntity.put("total", outputRate);
        returnEntity.put("answers", outputCorrectAnswer);
        return returnEntity;
    }

    // Update one Reading-Answer-Package-Information by ID
    // If the Reading-Answer-ID has not found, the result will be null
    public ReadingAnswer updateReadingAnswer(ReadingAnswer newReadingaAnswer, int rea_ans_id) {
        ReadingAnswer updatedReadingaAnswer = readingAnswerRepository.findById(rea_ans_id).get();
        if (updatedReadingaAnswer != null && rea_ans_id == newReadingaAnswer.getRea_ans_id()) {
            updatedReadingaAnswer = readingAnswerMapper.toReadingAnswer(newReadingaAnswer);
            readingAnswerRepository.save(updatedReadingaAnswer);
            return updatedReadingaAnswer;
        }
        return null;
    }

    // Delete Reading Answer Package by ID
    // If the Reading Answer Package is present the result will delete
    // If the Reading Answer Package is not present the result will be false
    public boolean deleteReadingAnswer(int rea_ans_id) {
        if (readingAnswerRepository.existsById(rea_ans_id)) {
            readingAnswerRepository.deleteById(rea_ans_id);
            return true;
        }
        return false;
    }
}
