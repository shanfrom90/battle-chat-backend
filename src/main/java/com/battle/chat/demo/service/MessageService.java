package com.battle.chat.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battle.chat.demo.model.Message;
import com.battle.chat.demo.socketIO.service.SocketIOService;
import com.corundumstudio.socketio.SocketIOClient;

import lombok.RequiredArgsConstructor;

import com.battle.chat.demo.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final SocketIOService socketService;

    @Autowired
    MessageRepository messageRepository;

    @SuppressWarnings({ "null" })
    public Message newMessage(Message message, SocketIOClient senderClient) {
        socketService.sendSocketMessage(senderClient, message);
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    @SuppressWarnings("null")
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }

}
