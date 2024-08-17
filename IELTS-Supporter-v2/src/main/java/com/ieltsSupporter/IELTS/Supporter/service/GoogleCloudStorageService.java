package com.ieltsSupporter.IELTS.Supporter.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.ByteString;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

@Service
public class GoogleCloudStorageService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private final String bucketName = "sample-speaking";

    public String uploadFile(MultipartFile file) throws IOException {
        String blobName = file.getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, blobName).build();
        storage.create(blobInfo, file.getBytes());

        return "gs://" + bucketName + "/" + blobName;
    }

    public String uploadFileAndGetPublicURL(ByteString inputStream) throws IOException {
        String fileName = getRandomName();
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).setContentType("audio/wav").build();

        // Upload the file
        storage.create(blobInfo, inputStream.toByteArray());
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }

    public static String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generates a random digit between 0 and 9
            sb.append(digit);
        }

        return sb.toString();
    }

    public static String getRandomName() {
        return generateRandomDigits(10) + ".wav";
    }

}
