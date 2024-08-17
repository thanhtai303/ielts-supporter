package com.ieltsSupporter.IELTS.Supporter.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.ByteString;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.dto.TextToSpeechRequest;
import com.ieltsSupporter.IELTS.Supporter.service.GoogleCloudStorageService;
import com.ieltsSupporter.IELTS.Supporter.service.OpenAIService;
import com.ieltsSupporter.IELTS.Supporter.service.TextToSpeechService;

@RestController
@RequestMapping("/data/text")
@CrossOrigin(origins = "http://localhost:3000")
public class TextToSpeechController {

    @Autowired
    TextToSpeechService textToSpeechService;

    @Autowired
    GoogleCloudStorageService googleCloudStorageService;

    @Autowired
    OpenAIService openAIService;

    @PostMapping("convert")
    public ResponseEntity<ResponseObject> uploadText(@RequestBody TextToSpeechRequest textToSpeechRequest)
            throws Exception {
        try {
            String sampleAnswer = openAIService.getSampleSpeakingScriptFromOpenAI(textToSpeechRequest.getQuestion());
            ByteString inputStream = textToSpeechService.synthesizeText(sampleAnswer);
            HashMap<String, String> output = new HashMap<>();
            output.put("text", googleCloudStorageService.uploadFileAndGetPublicURL(inputStream));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("OK", "Convert speech to text successful", output));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("FAILED", "Convert failed", null));
        }
    }

}
