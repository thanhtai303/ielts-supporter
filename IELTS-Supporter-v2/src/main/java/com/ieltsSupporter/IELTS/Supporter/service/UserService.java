package com.ieltsSupporter.IELTS.Supporter.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ieltsSupporter.IELTS.Supporter.model.User;
import com.ieltsSupporter.IELTS.Supporter.repository.UserRepository;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean createAccount(User newUser) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] inputBytes = newUser.getEncrypted_password().getBytes("UTF-8");
        byte[] hashBytes = digest.digest(inputBytes);
        String hashString = bytesToHex(hashBytes);
        newUser.setEncrypted_password(hashString);
        if (userRepository.findByEmail(newUser.getEmail()) == null) {
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    public boolean login(String password, String email) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] inputBytes = password.getBytes("UTF-8");
        byte[] hashBytes = digest.digest(inputBytes);
        String hashString = bytesToHex(hashBytes);
        User foundUser = userRepository.findByEmail(email);
        if (foundUser == null) {
            return false;
        }
        if (foundUser.getEncrypted_password().compareTo(hashString) == 0) {
            return true;
        }
        return false;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
