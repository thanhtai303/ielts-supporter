package com.ieltsSupporter.IELTS.Supporter.controller;

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
import com.ieltsSupporter.IELTS.Supporter.service.ReadingAnswerService;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.model.ReadingAnswer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data/reading/rea_ans")
@CrossOrigin(origins = "http://localhost:3000")
public class ReadingAnswerController {

    @Autowired
    private ReadingAnswerService readingAnswerService;

    // Get all Reading Answer Packages
    // Example: (Method GET) http://localhost:8080/data/reading/rea_ans/get
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getAllReadingAnswer() {
        List<ReadingAnswer> allReadingAnswer = readingAnswerService.queryAllReadingAnswer();
        return allReadingAnswer.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("FAILED", "Can not find Reading answers",
                                null))
                : ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Query all Reading answers succesful",
                                allReadingAnswer));
    }

    // Get one Reading Answer Packages by ID
    // Example: (Method GET) http://localhost:8080/data/reading/rea_ans/get/1
    @GetMapping("/get/{rea_ans_id}")
    public ResponseEntity<ResponseObject> getAnswerByTestID(@PathVariable int rea_ans_id) {
        Optional<ReadingAnswer> foundReadingAnswer = readingAnswerService
                .queryReadingAnswerByID(rea_ans_id);
        return foundReadingAnswer.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Query Reading answers succesful",
                                foundReadingAnswer))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("FAILED",
                                "Can not find Reading answers with ID=" + rea_ans_id,
                                null));
    }

    // Add new Reading Answer Package to Database
    // Example: (Method POST) http://localhost:8080/data/reading/rea_ans/insert
    // (Body Request)
    // {"rea_ans_id": {Reading answer package ID}, "rea_id": {Reading test ID},
    // "rea_answer_1": "{data}", "rea_answer_2": "{data}","rea_answer_3": "{data}",
    // {......}
    // "rea_answer_39": "{data}", "rea_answer_40": "{data}"}
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertReadingAnswer(@RequestBody ReadingAnswer newReadingAnswer) {
        ReadingAnswer foundReadingAnswer = readingAnswerService.addNewReadingAnswer(newReadingAnswer);
        if (foundReadingAnswer != null
                && newReadingAnswer.getRea_id() == newReadingAnswer.getRea_ans_id()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                    "Insert Reading answers successful",
                    foundReadingAnswer));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("FAILED", "ID of Reading answers has already tanken", null));
    }

    // Calculate the overall score
    // Example: (Method POST) http://localhost:8080/data/reading/rea_ans/answer
    // (Body Request)
    // {"rea_ans_id": {Reading answer package ID}, "rea_id": {Reading test ID},
    // "rea_answer_1": "{data}", "rea_answer_2": "{data}","rea_answer_3": "{data}",
    // {......}
    // "rea_answer_39": "{data}", "rea_answer_40": "{data}"}
    @PostMapping("/answer")
    public ResponseEntity<ResponseObject> getTestResult(@RequestBody ReadingAnswer newReadingAnswer) {
        var returnEntity = readingAnswerService.calculateReadingScore(newReadingAnswer);
        if (returnEntity != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    new ResponseObject("OK", "Querry the score succesful", returnEntity));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ResponseObject("FAILED", "Can not calculate", null));
    }

    // Update one Reading-Answer-Package-Information by ID
    // Example: (Method PUT) http://localhost:8080/data/reading/rea_ans/update/1
    // (Body Request)
    // {"rea_ans_id": {Reading answer package ID}, "rea_id": {Reading test ID},
    // "rea_answer_1": "{data}", "rea_answer_2": "{data}","rea_answer_3": "{data}",
    // {......}
    // "rea_answer_39": "{data}", "rea_answer_40": "{data}"}
    @PutMapping("/update/{lis_ans_id}")
    public ResponseEntity<ResponseObject> updateReadingAnswer(@RequestBody ReadingAnswer newReadingAnswer,
            @PathVariable int rea_ans_id) {
        ReadingAnswer updateReadingAnswer = readingAnswerService.updateReadingAnswer(newReadingAnswer,
                rea_ans_id);
        if (updateReadingAnswer != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Update Reading answers successful",
                            updateReadingAnswer));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                "Can not update Reading answer package with ID = " + rea_ans_id, null));
    }

    // Delete Reading Answer Package by ID
    // Example: (Method DELETE)
    // http://localhost:8080/data/reading/rea_ans/delete/1
    @DeleteMapping("/delete/{rea_ans_id}")
    public ResponseEntity<ResponseObject> deleteReadingAnswer(@PathVariable int rea_ans_id) {
        return readingAnswerService.deleteReadingAnswer(
                rea_ans_id)
                        ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                                "Delete Reading answer successful", null))
                        : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                new ResponseObject("FAILED",
                                        "Can not find and delete Reading answer",
                                        null));
    }
}
