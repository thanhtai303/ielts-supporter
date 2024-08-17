package com.ieltsSupporter.IELTS.Supporter.service;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import com.ieltsSupporter.IELTS.Supporter.model.Speaking;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    static final String projectId = "ielts-supporter";
    static final String location = "us-central1";
    static final String modelName = "gemini-1.5-flash-001";

    // Send question to OPENAI and get Response answer
    public String getWritingSampleFromOpenAI(String taskName, int task)
            throws IOException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            String imageUri = "gs://writingdata/" + taskName + ".jpg";
            GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
                    PartMaker.fromMimeTypeAndData("image/png", imageUri),
                    "write me an essay of this ielts writing " + Integer.toString(task)));
            return ResponseHandler.getText(response);
        }
    }

    // Score Writing test
    public String getWritingScoreFromOpenAI(int testID, String answer_1, String answer_2)
            throws IOException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            String imageUri_1 = "gs://writingdata/wri00" + testID + "01.jpg";
            String imageUri_2 = "gs://writingdata/wri00" + testID + "02.jpg";
            GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
                    PartMaker.fromMimeTypeAndData("image/png", imageUri_1),
                    PartMaker.fromMimeTypeAndData("image/png", imageUri_2),
                    "give me exactly score and short feedback of this IELTS writing submition, them calculate overall score:\nanswer of task 1: "
                            + answer_1 + "\n answer of task 2: " + answer_2));

            return ResponseHandler.getText(response);
        }
    }

    // Get sample script for Speaking test
    public String getSampleSpeakingScriptFromOpenAI(String question)
            throws IOException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
                    "give me answer of this speaking task in IELTS: " + question));

            return ResponseHandler.getText(response);
        }
    }

    // Score Speaking test by script
    public String scoreSpeakingTestByOpenAI(String answer, Speaking speaking)
            throws IOException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
                    PartMaker.fromMimeTypeAndData("audio/wav", "gs://sample-speaking/recording.wav"),
                    "give exactly score and short feedback of this IELTS speaking test with question is: "
                            + speaking.toString()));

            return ResponseHandler.getText(response);
        }
    }
}
