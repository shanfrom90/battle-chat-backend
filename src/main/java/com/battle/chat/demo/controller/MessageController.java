package com.battle.chat.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battle.chat.demo.model.Message;
import com.battle.chat.demo.service.MessageService;

import jakarta.persistence.Id;
import jakarta.websocket.server.PathParam;

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
    
    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public String deleteMessageById(@PathParam("id") Id messageId){
          messageService.deleteMessage(messageId);
          return "Deleted message with ID:" + messageId;
    }

    

    
}
