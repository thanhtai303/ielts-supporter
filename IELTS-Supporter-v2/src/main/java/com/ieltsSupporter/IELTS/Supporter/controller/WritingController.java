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
import com.ieltsSupporter.IELTS.Supporter.model.Writing;
import com.ieltsSupporter.IELTS.Supporter.service.WritingService;

@RestController
@RequestMapping("/data/writing/wri_ques")
@CrossOrigin(origins = "http://localhost:3000")
public class WritingController {

    @Autowired
    private WritingService writingService;

    // Get all Writing Test
    // Example: (Method GET) http://localhost:8080/data/writing/wri_ques/get
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getAllWritingTest() {
        List<Writing> allWritingTest = writingService.getAllWritingTest();
        return allWritingTest.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("FAILED", "Can not find Writing tests",
                                null))
                : ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Query all Writing test succesful",
                                allWritingTest));
    }

    // Get one Writing Test by ID
    // Example: (Method GET) http://localhost:8080/data/writing/wri_ques/get/1
    @GetMapping("/get/{wri_id}")
    public ResponseEntity<ResponseObject> getWritingTestByID(@PathVariable int wri_id) {
        Optional<Writing> foundWritingTest = writingService.getWritingTestByID(wri_id);
        return foundWritingTest.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Query Writing test succesful",
                                foundWritingTest))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("FAILED",
                                "Can not find Writing test with ID=" + wri_id,
                                null));
    }

    // Add new Writing Test to Database
    // Example: (Method POST) http://localhost:8080/data/writing/wri_ques/insert
    // (Body Request)
    // {"wri_id": {Writing test ID},"wri_topic": "{Writing test name}",
    // "wri_question_1": "{data}","wri_question_1_url": "{data}",
    // "wri_question_2": "{data}","wri_question_2_url": "{data}"}
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertWritingTest(@RequestBody Writing newWriting) {
        Writing foundWriting = writingService.addNewWritingTest(newWriting);
        if (foundWriting == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("FAILED", "ID of Writing test has already taken", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                "Insert Writing test successful", foundWriting));

    }

    // Update one Writing-Test-Information by ID
    // Example: (Method PUT) http://localhost:8080/data/writing/wri_ques/update
    // (Body Request)
    // {"wri_id": {Writing test ID},"wri_topic": "{Writing test name}",
    // "wri_question_1": "{data}","wri_question_1_url": "{data}",
    // "wri_question_2": "{data}","wri_question_2_url": "{data}"}
    @PutMapping("update/{wri_id}")
    public ResponseEntity<ResponseObject> updateWritingTest(@RequestBody Writing newWriting,
            @PathVariable int wri_id) {
        Writing updateWriting = writingService.updateWritingTest(newWriting, wri_id);
        if (updateWriting != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Update Writing test successful", updateWriting));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                "Can not update Writing test with ID = " + wri_id, null));
    }

    // Delete Writing Test by ID
    // Example: (Method DELETE)
    // http://localhost:8080/data/writing/wri_ques/delete/1
    @DeleteMapping("/delete/{wri_id}")
    public ResponseEntity<ResponseObject> deleteWritingTest(@PathVariable int wri_id) {
        return writingService.deleteWritingTest(wri_id)
                ? ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Delete Writing test successful", null))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                        "Can not find and delete Writing test with ID = " + wri_id, null));
    }

}
