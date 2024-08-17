package com.ieltsSupporter.IELTS.Supporter.service;

import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import org.springframework.stereotype.Service;

@Service
public class SpeechToTextService {

    public String transcribeGcsAudio(String gcsUri) {
        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setLanguageCode("en-US")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(gcsUri)
                    .build();

            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response = speechClient
                    .longRunningRecognizeAsync(config, audio);

            LongRunningRecognizeResponse longResponse = response.get();

            StringBuilder transcription = new StringBuilder();
            for (SpeechRecognitionResult result : longResponse.getResultsList()) {
                for (SpeechRecognitionAlternative alternative : result.getAlternativesList()) {
                    transcription.append(alternative.getTranscript()).append("\n");
                }
            }
            return transcription.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error transcribing audio: " + e.getMessage();
        }
    }
}