package com.ieltsSupporter.IELTS.Supporter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieltsSupporter.IELTS.Supporter.mapper.ListeningMapper;
import com.ieltsSupporter.IELTS.Supporter.model.Listening;
import com.ieltsSupporter.IELTS.Supporter.repository.ListeningRepository;

@Service
public class ListeningService {

    @Autowired
    private ListeningRepository listeningRepository;

    @Autowired
    private ListeningMapper listeningMapper;

    // Query all Listening Tests from Database
    public List<Listening> queryAllListeningTest() {
        return listeningRepository.findAll();
    }

    // Query one Listening Test by ID
    public Optional<Listening> queryListeningTestByID(int lis_id) {
        return listeningRepository.findById(lis_id);
    }

    // Add more Listening Test to Database
    // If the New-Listening-Test-ID has been taken, the result is null
    public Listening addNewListeningTest(Listening newListening) {
        if (listeningRepository.findById(newListening.getLis_id()).isPresent()) {
            return null;
        }
        return listeningRepository.save(newListening);
    }

    // Update one Listening-Test-Information by ID
    // If the Listening-Test-ID has not found, the result will be null
    public Listening updateListeningTest(Listening newListening, int lis_id) {
        Listening updatedListening = listeningRepository.findById(lis_id).get();
        if (updatedListening != null && lis_id == newListening.getLis_id()) {
            updatedListening = listeningMapper.toListening(newListening);
            listeningRepository.save(updatedListening);
            return updatedListening;
        }
        return null;
    }

    // Delete Listening Test by ID
    // If the Listening Test is present the result will delete it from Database
    // If the Listening Test is not present the result will be false
    public boolean deleteListeningTest(int lis_id) {
        if (listeningRepository.existsById(lis_id)) {
            listeningRepository.deleteById(lis_id);
            return true;
        }
        return false;
    }
}
