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
import com.ieltsSupporter.IELTS.Supporter.service.ListeningAnswerService;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.model.ListeningAnswer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data/listening/lis_ans")
@CrossOrigin(origins = "http://localhost:3000")
public class ListeningAnswerController {

        @Autowired
        private ListeningAnswerService listeningAnswerService;

        // Get all Listening Answer Packages
        // Example: (Method GET) http://localhost:8080/data/listening/lis_ans/get
        @GetMapping("/get")
        public ResponseEntity<ResponseObject> getAllListeningAnswer() {
                List<ListeningAnswer> allListeningAnswer = listeningAnswerService.queryAllListeningAnswer();
                return allListeningAnswer.isEmpty()
                                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED", "Can not find listening answers",
                                                                null))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query all listening answers succesful",
                                                                allListeningAnswer));
        }

        // Get one Listening Answer Packages by ID
        // Example: (Method GET) http://localhost:8080/data/listening/lis_ans/get/1
        @GetMapping("/get/{lis_ans_id}")
        public ResponseEntity<ResponseObject> getAnswerByTestID(@PathVariable int lis_ans_id) {
                Optional<ListeningAnswer> foundListeningAnswer = listeningAnswerService
                                .queryListeningAnswerByID(lis_ans_id);
                return foundListeningAnswer.isPresent()
                                ? ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query listening answers succesful",
                                                                foundListeningAnswer))
                                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED",
                                                                "Can not find listening answers with ID=" + lis_ans_id,
                                                                null));
        }

        // Add new Listening Answer Package to Database
        // Example: (Method POST) http://localhost:8080/data/listening/lis_ans/insert
        // (Body Request)
        // {"lis_ans_id": {listening answer package ID}, "lis_id": {listening test ID},
        // "lis_answer_1": "{data}", "lis_answer_2": "{data}","lis_answer_3": "{data}",
        // {......}
        // "lis_answer_39": "{data}", "lis_answer_40": "{data}"}
        @PostMapping("/insert")
        public ResponseEntity<ResponseObject> insertListeningAnswer(@RequestBody ListeningAnswer newListeningAnswer) {
                ListeningAnswer foundListeningAnswer = listeningAnswerService.addNewListeningAnswer(newListeningAnswer);
                if (foundListeningAnswer != null
                                && newListeningAnswer.getLis_id() == newListeningAnswer.getLis_ans_id()) {
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                                        "Insert listening answers successful",
                                        foundListeningAnswer));
                }
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                                new ResponseObject("FAILED", "ID of listening answers has already tanken", null));
        }

        // Calculate the overall score
        // Example: (Method POST) http://localhost:8080/data/listening/lis_ans/answer
        // (Body Request)
        // {"lis_ans_id": {listening answer package ID}, "lis_id": {listening test ID},
        // "lis_answer_1": "{data}", "lis_answer_2": "{data}","lis_answer_3": "{data}",
        // {......}
        // "lis_answer_39": "{data}", "lis_answer_40": "{data}"}
        @PostMapping("/answer")
        public ResponseEntity<ResponseObject> getTestResult(@RequestBody ListeningAnswer newListeningAnswer) {
                var returnEntity = listeningAnswerService.calculateListeningScore(newListeningAnswer);
                if (returnEntity != null) {
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                                        new ResponseObject("OK", "Querry the score succesful", returnEntity));
                }
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                                .body(new ResponseObject("FAILED", "Can not calculate", null));
        }

        // Update one Listening-Answer-Package-Information by ID
        // Example: (Method PUT) http://localhost:8080/data/listening/lis_ans/update/1
        // (Body Request)
        // {"lis_ans_id": {listening answer package ID}, "lis_id": {listening test ID},
        // "lis_answer_1": "{data}", "lis_answer_2": "{data}","lis_answer_3": "{data}",
        // {......}
        // "lis_answer_39": "{data}", "lis_answer_40": "{data}"}
        @PutMapping("/update/{lis_ans_id}")
        public ResponseEntity<ResponseObject> updateListeningAnswer(@RequestBody ListeningAnswer newListeningAnswer,
                        @PathVariable int lis_ans_id) {
                ListeningAnswer updateListeningAnswer = listeningAnswerService.updateListeningAnswer(newListeningAnswer,
                                lis_ans_id);
                if (updateListeningAnswer != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ResponseObject("OK", "Update listening answers successful",
                                                        updateListeningAnswer));
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                                "Can not update listening answer package with ID = " + lis_ans_id, null));
        }

        // Delete Listening Answer Package by ID
        // Example: (Method DELETE)
        // http://localhost:8080/data/listening/lis_ans/delete/1
        @DeleteMapping("/delete/{lis_ans_id}")
        public ResponseEntity<ResponseObject> deleteListeningAnswer(@PathVariable int lis_ans_id) {
                return listeningAnswerService.deleteListeningAnswer(
                                lis_ans_id) ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                                                "Delete listening answer successful", null))
                                                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                                                new ResponseObject("FAILED",
                                                                                "Can not find and delete listening answer",
                                                                                null));
        }
}
