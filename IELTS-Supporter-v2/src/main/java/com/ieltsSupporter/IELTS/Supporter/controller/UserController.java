package com.ieltsSupporter.IELTS.Supporter.controller;

import org.springframework.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ieltsSupporter.IELTS.Supporter.dto.ResponseObject;
import com.ieltsSupporter.IELTS.Supporter.dto.UserLoginRequest;
import com.ieltsSupporter.IELTS.Supporter.model.User;
import com.ieltsSupporter.IELTS.Supporter.service.UserService;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody UserLoginRequest newLoginRequest)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(newLoginRequest.getPassword());
        if (userService.login(newLoginRequest.getPassword(), newLoginRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Login successful", true));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("FAILED", "Login failed", false));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody User newUser)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return userService.createAccount(newUser)
                ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Create account successful", true))
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ResponseObject("FAILED", "Email has already used", false));
    }
}
