package com.battle.chat.demo.socketIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;

@CrossOrigin
@Component
@Log4j2
public class SocketIOConfig {

    @Value("${socket.host}")
    private String SOCKETHOST;

    @Value("${socket.port}")
    private int SOCKETPORT;

    private SocketIOServer socketServer;

    @Bean
    public SocketIOServer socketIOServer(){
        Configuration config = new Configuration();
        config.setHostname(SOCKETHOST);
        config.setPort(SOCKETPORT);

        socketServer = new SocketIOServer(config);
        socketServer.start();
        socketServer.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client){
                log.info("new user connected with socket " + client.getSessionId());
            }
        });
        socketServer.addDisconnectListener(new DisconnectListener(){
            @Override
            public void onDisconnect(SocketIOClient client){
                client.getNamespace().getAllClients().stream().forEach(data -> {
                    log.info("user disconnected " + data.getSessionId().toString());
                });
            }
        });
        return socketServer;
    }

  @PreDestroy
    public void stopSocketIOServer(){
        this.socketServer.stop();
    }
}
