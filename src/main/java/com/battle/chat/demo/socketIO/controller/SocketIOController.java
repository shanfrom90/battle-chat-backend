package com.battle.chat.demo.socketIO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.battle.chat.demo.model.Message;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SocketIOController {
    
    @Autowired
    private SocketIOServer socketServer; 

    SocketIOController(SocketIOServer socketIOServer){
        this.socketServer = socketIOServer; 
        this.socketServer.addConnectListener(onUserConnectWithSocket);
        this.socketServer.addDisconnectListener(onUserDisconnectWithSocket);
        this.socketServer.addEventListener("sendMessage", Message.class, onSendMessage);
    }

    public ConnectListener onUserConnectWithSocket = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient client){
            log.info("On connect operation inside controller");
        }
    };

    public DisconnectListener onUserDisconnectWithSocket = new  DisconnectListener() {
        @Override
        public void onDisconnect(SocketIOClient client){
            log.info("On disconnect operation inside controller");
        }
    };

    public DataListener<Message> onSendMessage = new DataListener<Message>() {
        @Override
        public void onData(SocketIOClient client, Message message, AckRequest acknowledge) throws Exception{
            log.info(message.getSenderName()+" user sends message to user " + message.getTargetName() + " with the message: " + message.getTextMessage());
            socketServer.getBroadcastOperations().sendEvent(message.getTargetName(), client, message); 
            acknowledge.sendAckData("Message sent to target user sucessfully"); 
        }
    };
}
