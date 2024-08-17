package com.ieltsSupporter.IELTS.Supporter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ieltsSupporter.IELTS.Supporter.dto.WritingSampleRequestObject;
import com.ieltsSupporter.IELTS.Supporter.dto.WritingScoreRequestObject;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.service.OpenAIService;

import java.util.HashMap;
import java.io.IOException;

@RestController
@RequestMapping("/data/openai")
@CrossOrigin(origins = "http://localhost:3000")
public class OpenAIController {

        @Autowired
        OpenAIService openAIService;

        // Post question and the system will return the answer from OpenAI
        // Example: (Method POST) http://localhost:8080/data/openai/ask
        // (Body Request)
        // {"responseType": "json", "question": "{question}"}
        @PostMapping("/ask")
        public ResponseEntity<ResponseObject> returnWritingSampleOpenAIi(
                        @RequestBody WritingSampleRequestObject newRequestObject)
                        throws IOException {
                String responseString = openAIService.getWritingSampleFromOpenAI(newRequestObject.getTaskName(),
                                newRequestObject.getTask());
                HashMap<String, String> responseObject = new HashMap<>();
                responseObject.put("answer", responseString);
                return responseString.isEmpty()
                                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED",
                                                                "OpenAI can not answer your question", null))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Query answer from OpenAI successful",
                                                                responseObject));
        }

        @PostMapping("/score")
        public ResponseEntity<ResponseObject> returnWritingScoreOpenAIi(
                        @RequestBody WritingScoreRequestObject newScoreRequestObject)
                        throws IOException {
                String responseString = openAIService.getWritingScoreFromOpenAI(newScoreRequestObject.getTestID(),
                                newScoreRequestObject.getAnswer_1(), newScoreRequestObject.getAnswer_2());
                HashMap<String, String> responseObject = new HashMap<>();
                responseObject.put("answer", responseString);
                return responseString.isEmpty()
                                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(new ResponseObject("FAILED",
                                                                "OpenAI can not answer your question", null))
                                : ResponseEntity.status(HttpStatus.OK)
                                                .body(new ResponseObject("OK", "Score from OpenAI successful",
                                                                responseObject));
        }

}
