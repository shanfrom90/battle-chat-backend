package com.battle.chat.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battle.chat.demo.model.Message;

import com.battle.chat.demo.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @SuppressWarnings({ "null" })
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    @SuppressWarnings("null")
    public void deleteMessage( Long messageId) {
        messageRepository.deleteById(messageId);
    }

    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }

}
