package com.battle.chat.demo.socketIO.service;

import org.springframework.stereotype.Service;

import com.battle.chat.demo.model.Message;
import com.battle.chat.demo.service.MessageService;
import com.corundumstudio.socketio.SocketIOClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocketIOService {
    private final MessageService messageService;

     public void sendSocketMessage(SocketIOClient senderClient, Message message){

        for(
            SocketIOClient client: senderClient.getNamespace().getAllClients()
        ){
            if(!client.getSessionId().equals(senderClient.getSessionId())){
                client.sendEvent("sendMessage", message);
                System.out.println("inside sendSocketMessage" + message);
            }
        }
        
    }

    public void saveSocketMessage(SocketIOClient senderClient, Message message) {

    System.out.println("inside save Socket message=========");
    Message storedMessage = messageService.saveMessage(
            Message.builder().textMessage(message.getTextMessage()).userName(message.getUserName()).build());

    sendSocketMessage(senderClient, storedMessage);
    }

}
