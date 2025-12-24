package com.mateusz_pszczola.user_service.controller;

import com.mateusz_pszczola.user_service.model.SignUpReq;
import com.mateusz_pszczola.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@RestController
@RequestMapping("/api")
public class Controller{
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpReq> signUp(@RequestBody SignUpReq req){
        userService.createUser(req.getEmail(), req.getPassword());
        //TODO: CHECK IF SUCCESSFUL
        return ResponseEntity.status(HttpStatus.OK).body(req);
    }
}