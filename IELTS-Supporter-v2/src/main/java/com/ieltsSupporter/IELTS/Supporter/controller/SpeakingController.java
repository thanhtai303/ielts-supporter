package com.ieltsSupporter.IELTS.Supporter.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.model.Speaking;
import com.ieltsSupporter.IELTS.Supporter.service.SpeakingService;

@RestController
@RequestMapping("/data/speaking/spe_ques")
@CrossOrigin(origins = "http://localhost:3000")
public class SpeakingController {

    @Autowired
    private SpeakingService speakingService;

    // Get all Speaking Test
    // Example: (Method GET) http://localhost:8080/data/speaking/spe_ques/get
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getAllSpeakingTest() {
        List<Speaking> allSpeakingTest = speakingService.getAllSpeakingTest();
        return allSpeakingTest.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("FAILED", "Can not find Speaking tests",
                                null))
                : ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Query all Speaking test succesful",
                                allSpeakingTest));
    }

    // Get one Speaking Test by ID
    // Example: (Method GET) http://localhost:8080/data/speaking/spe_ques/get/1
    @GetMapping("/get/{spe_id}")
    public ResponseEntity<ResponseObject> getSpeakingTestByID(@PathVariable int spe_id) {
        Optional<Speaking> foundSpeakingTest = speakingService.getSpeakingTestByID(spe_id);
        return foundSpeakingTest.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Query Speaking test succesful",
                                foundSpeakingTest))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("FAILED",
                                "Can not find Speaking test with ID=" + spe_id,
                                null));
    }

    // Add new Speaking Test to Database
    // Example: (Method POST) http://localhost:8080/data/speaking/spe_ques/insert
    // (Body Request)
    // {"spe_id": {Speaking test ID},"spe_topic": "{Speaking test name}",
    // "spe_question_1_1": "{data}","spe_question_1_2": "{data}","spe_question_1_3":
    // "{data}", "spe_question_2": "{data}", "spe_question_2_suggest_1": "{data}",
    // "spe_question_2_suggest_2": "{data}","spe_question_2_suggest_3":
    // "{data}","spe_question_2_suggest_4": "{data}","spe_question_3_1":
    // "{data}","spe_question_3_2": "{data}","spe_question_3_3": "{data}",
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertSpeakingTest(@RequestBody Speaking newSpeaking) {
        Speaking foundSpeaking = speakingService.addNewSpeakingTest(newSpeaking);
        if (foundSpeaking == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("FAILED", "ID of Speaking test has already taken", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                "Insert Speaking test successful", foundSpeaking));

    }

    // Update one Speaking-Test-Information by ID
    // Example: (Method PUT) http://localhost:8080/data/speaking/spe_ques/update
    // (Body Request)
    // {"spe_id": {Speaking test ID},"spe_topic": "{Speaking test name}",
    // "spe_question_1_1": "{data}","spe_question_1_2": "{data}","spe_question_1_3":
    // "{data}", "spe_question_2": "{data}", "spe_question_2_suggest_1": "{data}",
    // "spe_question_2_suggest_2": "{data}","spe_question_2_suggest_3":
    // "{data}","spe_question_2_suggest_4": "{data}","spe_question_3_1":
    // "{data}","spe_question_3_2": "{data}","spe_question_3_3": "{data}",
    @PutMapping("update/{spe_id}")
    public ResponseEntity<ResponseObject> updateSpeakingTest(@RequestBody Speaking newSpeaking,
            @PathVariable int spe_id) {
        Speaking updateSpeaking = speakingService.updateSpeakingTest(newSpeaking, spe_id);
        if (updateSpeaking != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Update Speaking test successful", updateSpeaking));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                "Can not update Speaking test with ID = " + spe_id, null));
    }

    // Delete Speaking Test by ID
    // Example: (Method DELETE)
    // http://localhost:8080/data/speaking/spe_ques/delete/1
    @DeleteMapping("/delete/{spe_id}")
    public ResponseEntity<ResponseObject> deleteSpeakingTest(@PathVariable int spe_id) {
        return speakingService.deleteSpeakingTest(spe_id)
                ? ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Delete Speaking test successful", null))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                        "Can not find and delete Speaking test with ID = " + spe_id, null));
    }

}
