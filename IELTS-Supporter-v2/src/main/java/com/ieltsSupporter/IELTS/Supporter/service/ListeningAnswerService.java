package com.ieltsSupporter.IELTS.Supporter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieltsSupporter.IELTS.Supporter.mapper.ListeningAnswerMapper;
import com.ieltsSupporter.IELTS.Supporter.model.ListeningAnswer;
import com.ieltsSupporter.IELTS.Supporter.repository.ListeningAnswerRepository;

@Service
public class ListeningAnswerService {

    @Autowired
    ListeningAnswerRepository listeningAnswerRepository;

    @Autowired
    ListeningAnswerMapper listeningAnswerMapper;

    // Query all Listening Answer Package from Database
    public List<ListeningAnswer> queryAllListeningAnswer() {
        return listeningAnswerRepository.findAll();
    }

    // Query Query one Listening Answer Package by ID
    public Optional<ListeningAnswer> queryListeningAnswerByID(int lis_ans_id) {
        return listeningAnswerRepository.findById(lis_ans_id);
    }

    // Add more Listening Answer Package to Database
    // If the New-Listening-Answer-Package-ID has been taken, the result is null
    public ListeningAnswer addNewListeningAnswer(ListeningAnswer newListeningaAnswer) {
        if (listeningAnswerRepository.findById(newListeningaAnswer.getLis_ans_id()).isPresent()
                || newListeningaAnswer.getLis_ans_id() != newListeningaAnswer.getLis_id()) {
            return null;
        }
        return listeningAnswerRepository.save(newListeningaAnswer);
    }

    // Calculate the overall score
    // Return include overall score, all correct answers and status of each answer
    public HashMap<String, HashMap<String, String>> calculateListeningScore(ListeningAnswer newListeningAnswer) {
        Optional<ListeningAnswer> foundAnsers = listeningAnswerRepository.findById(newListeningAnswer.getLis_ans_id());
        if (foundAnsers.isEmpty()) {
            return null;
        }
        ListeningAnswer answer = foundAnsers.get();
        String[] checkAnswer = newListeningAnswer.allAnswer();
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

    // Update one Listening-Answer-Package-Information by ID
    // If the Listening-Answer-ID has not found, the result will be null
    public ListeningAnswer updateListeningAnswer(ListeningAnswer newListeningaAnswer, int lis_ans_id) {
        ListeningAnswer updatedListeningaAnswer = listeningAnswerRepository.findById(lis_ans_id).get();
        if (updatedListeningaAnswer != null && lis_ans_id == newListeningaAnswer.getLis_ans_id()) {
            updatedListeningaAnswer = listeningAnswerMapper.toListeningAnswer(newListeningaAnswer);
            listeningAnswerRepository.save(updatedListeningaAnswer);
            return updatedListeningaAnswer;
        }
        return null;
    }

    // Delete Listening Answer Package by ID
    // If the Listening Answer Package is present the result will delete it
    // If the Listening Answer Package is not present the result will be false
    public boolean deleteListeningAnswer(int lis_ans_id) {
        if (listeningAnswerRepository.existsById(lis_ans_id)) {
            listeningAnswerRepository.deleteById(lis_ans_id);
            return true;
        }
        return false;
    }
}
