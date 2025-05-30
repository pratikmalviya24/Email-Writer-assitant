package com.programmer.email_writer_assitant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.programmer.email_writer_assitant.dto.EmailRequest;
import com.programmer.email_writer_assitant.service.EmailGeneratorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/email")
public class EmailGeneratorController {
    @Autowired
    private  EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest){
        String response = emailGeneratorService.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response );
    }
}
