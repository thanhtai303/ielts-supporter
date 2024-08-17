package com.ieltsSupporter.IELTS.Supporter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.dto.SpeechToTextRequest;
import com.ieltsSupporter.IELTS.Supporter.model.Speaking;
import com.ieltsSupporter.IELTS.Supporter.service.GoogleCloudStorageService;
import com.ieltsSupporter.IELTS.Supporter.service.OpenAIService;
import com.ieltsSupporter.IELTS.Supporter.service.SpeakingService;
import com.ieltsSupporter.IELTS.Supporter.service.SpeechToTextService;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/data/speech")
@CrossOrigin(origins = "http://localhost:3000")
public class SpeechToTextController {

    @Autowired
    GoogleCloudStorageService storageService;

    @Autowired
    SpeakingService speakingService;

    @Autowired
    SpeechToTextService speechToTextService;

    @Autowired
    OpenAIService openAIService;

    @PostMapping("/convert")
    public ResponseEntity<ResponseObject> uploadFileAudioAndScore(SpeechToTextRequest newSpeechToTextRequest) {
        try {
            String url = storageService.uploadFile(newSpeechToTextRequest.getFile());

            HashMap<String, String> output = new HashMap<>();
            String answer = speechToTextService.transcribeGcsAudio(url);
            Speaking speaking = speakingService.getSpeakingTestByID(newSpeechToTextRequest.getSpe_id()).get();
            String feedback = openAIService.scoreSpeakingTestByOpenAI(answer, speaking);
            output.put("feedback", feedback);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("OK", "Convert speech to text successful", output));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("FAILED", "Convert failed", null));
        }
    }
}
