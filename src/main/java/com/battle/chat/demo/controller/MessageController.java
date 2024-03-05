package com.battle.chat.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battle.chat.demo.model.Message;
import com.battle.chat.demo.service.MessageService;
import com.corundumstudio.socketio.SocketIOClient;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<?> newMessage(@RequestBody Message message, SocketIOClient socketClient) {
        messageService.newMessage(message, socketClient);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public String deleteMessageById(@PathVariable("id") Long messageId) {
        messageService.deleteMessage(messageId);
        return "Deleted message with ID:" + messageId;
    }

    @DeleteMapping("/deleteAllMessages")
    public String deleteAllMessages() {
        messageService.deleteAllMessages();
        return "Deleted all messages";
    }

}
