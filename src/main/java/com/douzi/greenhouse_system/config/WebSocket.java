package com.douzi.greenhouse_system.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@Slf4j
@Component
@ServerEndpoint(value="/a")
public class WebSocket {

    @OnOpen
    public void OnOpen(){}

}
