package com.battle.chat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battle.chat.demo.model.Message;
import com.battle.chat.demo.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody Message message ){
        messageService.saveMessage(message);

        return ResponseEntity.ok().build();
    }
    
}