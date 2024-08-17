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
import com.ieltsSupporter.IELTS.Supporter.service.ReadingService;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.model.Reading;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data/reading/rea_ques")
@CrossOrigin(origins = "http://localhost:3000")
public class ReadingController {

        @Autowired
        private ReadingService readingService;

        // Get all Reading Test
        // Example: (Method GET) http://localhost:8080/data/reading/rea_ques/get
        @GetMapping("/get")
        public ResponseEntity<ResponseObject> getAllReadingTest() {
                List<Reading> allReadingTest = readingService.getAllReadingTest();
                return allReadingTest.isEmpty()
                                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED", "Can not find reading tests",
                                                                null))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query all reading test succesful",
                                                                allReadingTest));
        }

        // Get one Reading Test by ID
        // Example: (Method GET) http://localhost:8080/data/reading/rea_ques/get/1
        @GetMapping("/get/{rea_id}")
        public ResponseEntity<ResponseObject> getReadingTestByID(@PathVariable int rea_id) {
                Optional<Reading> foundReadingTest = readingService.getReadingTestByID(rea_id);
                return foundReadingTest.isPresent()
                                ? ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query Reading test succesful",
                                                                foundReadingTest))
                                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED",
                                                                "Can not find Reading test with ID=" + rea_id,
                                                                null));
        }

        // Add new Reading Test to Database
        // Example: (Method POST) http://localhost:8080/data/reading/rea_ques/insert
        // (Body Request)
        // {"rea_id": {Reading test ID},"rea_topic": "{Reading test name}",
        // "rea_question_1":"{data}","rea_question_2":"{data}","rea_question_3":"{data}",
        // "rea_doc_1": "{data}","rea_doc_2": "{data}", "rea_doc_3": "{data}"}
        @PostMapping("/insert")
        public ResponseEntity<ResponseObject> insertReadingTest(@RequestBody Reading newReading) {
                Reading foundReading = readingService.addNewReadingTest(newReading);
                if (foundReading == null) {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                                        new ResponseObject("FAILED", "ID of Reading test has already taken", null));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",
                                "Insert Reading test successful", foundReading));

        }

        // Update one Reading-Test-Information by ID
        // Example: (Method PUT) http://localhost:8080/data/reading/rea_ques/update
        // (Body Request)
        // {"rea_id": {Reading test ID},"rea_topic": "{Reading test name}",
        // "rea_question_1":"{data}","rea_question_2":"{data}","rea_question_3":"{data}",
        // "rea_doc_1": "{data}","rea_doc_2": "{data}", "rea_doc_3": "{data}"}
        @PutMapping("update/{rea_id}")
        public ResponseEntity<ResponseObject> updateReadingTest(@RequestBody Reading newReading,
                        @PathVariable int rea_id) {
                Reading updateReading = readingService.updateReadingTest(newReading, rea_id);
                if (updateReading != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ResponseObject("OK", "Update Reading test successful", updateReading));
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                                "Can not update Reading test with ID = " + rea_id, null));
        }

        // Delete Reading Test by ID
        // Example: (Method DELETE)
        // http://localhost:8080/data/reading/rea_ques/delete/1
        @DeleteMapping("/delete/{rea_id}")
        public ResponseEntity<ResponseObject> deleteListeningTest(@PathVariable int rea_id) {
                return readingService.deleteReadingTest(rea_id)
                                ? ResponseEntity.status(HttpStatus.OK).body(
                                                new ResponseObject("OK", "Delete Reading test successful", null))
                                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("FAILED",
                                                "Can not find and delete Reading test with ID = " + rea_id, null));
        }

}
