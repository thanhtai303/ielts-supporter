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
import com.ieltsSupporter.IELTS.Supporter.service.ListeningService;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.model.Listening;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data/listening/lis_ques")
@CrossOrigin(origins = "http://localhost:3000")
public class ListeningController {

        @Autowired
        private ListeningService listeningService;

        // Get all Listening Test
        // Example: (Method GET) http://localhost:8080/data/listening/lis_ques/get
        @GetMapping("/get")
        public ResponseEntity<ResponseObject> getAllListeningTest() {
                List<Listening> allListeningTest = listeningService.queryAllListeningTest();
                return allListeningTest.isEmpty()
                                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED", "Can not find listening tests",
                                                                null))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query all listening test succesful",
                                                                allListeningTest));
        }

        // Get one Listening Test by ID
        // Example: (Method GET) http://localhost:8080/data/listening/lis_ques/get/1
        @GetMapping("/get/{lis_id}")
        public ResponseEntity<ResponseObject> getListeningTestByID(@PathVariable int lis_id) {
                Optional<Listening> foundListeningTest = listeningService.queryListeningTestByID(lis_id);
                return foundListeningTest.isPresent()
                                ? ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query listening test succesful",
                                                                foundListeningTest))
                                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED",
                                                                "Can not find listening test with ID=" + lis_id,
                                                                null));
        }

        // Add new Listening Test to Database
        // Example: (Method POST) http://localhost:8080/data/listening/lis_ques/insert
        // (Body Request)
        // {"lis_id": {listening test ID},"lis_topic": "{listening test name}",
        // "lis_question_1": "{data}","lis_question_2": "{data}",
        // "lis_question_3": "{data}","lis_question_4": "{data}",
        // "lis_audio_1": "{data}","lis_audio_2": "{data}",
        // "lis_audio_3": "{data}","lis_audio_4": "{data}"}
        @PostMapping("/insert")
        public ResponseEntity<ResponseObject> insertListeningTest(@RequestBody Listening newListening) {
                Listening foundListening = listeningService.addNewListeningTest(newListening);
                if (foundListening == null) {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                                        new ResponseObject("FAILED", "ID of listening test has already taken", null));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                                "Insert listening test successful", foundListening));

        }

        // Update one Listening-Test-Information by ID
        // Example: (Method PUT) http://localhost:8080/data/listening/lis_ques/update
        // (Body Request)
        // {"lis_id": {listening test ID},"lis_topic": "{listening test name}",
        // "lis_question_1": "{data}","lis_question_2": "{data}",
        // "lis_question_3": "{data}","lis_question_4": "{data}",
        // "lis_audio_1": "{data}","lis_audio_2": "{data}",
        // "lis_audio_3": "{data}","lis_audio_4": "{data}"}
        @PutMapping("update/{lis_id}")
        public ResponseEntity<ResponseObject> updateListeningTest(@RequestBody Listening newListening,
                        @PathVariable int lis_id) {
                Listening updateListening = listeningService.updateListeningTest(newListening, lis_id);
                if (updateListening != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ResponseObject("OK", "Update listening test successful", updateListening));
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                                "Can not update listening test with ID = " + lis_id, null));
        }

        // Delete Listening Test by ID
        // Example: (Method DELETE)
        // http://localhost:8080/data/listening/lis_ques/delete/1
        @DeleteMapping("/delete/{lis_id}")
        public ResponseEntity<ResponseObject> deleteListeningTest(@PathVariable int lis_id) {
                return listeningService.deleteListeningTest(lis_id)
                                ? ResponseEntity.status(HttpStatus.OK).body(
                                                new ResponseObject("OK", "Delete listening test successful", null))
                                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                                                "Can not find and delete listening test with ID = " + lis_id, null));
        }

}