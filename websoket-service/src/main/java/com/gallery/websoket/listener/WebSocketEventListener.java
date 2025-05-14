package com.gallery.websoket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

@Slf4j
@Component
public class WebSocketEventListener {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    /**
     * Name
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        log.info("SessionConnectEvent a new web socket connection:{}",event.toString());
    }

    /***
     * 监听连接消息
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection:{}",event.toString());
    }


    /***
     * 订阅消息
     * @param event
     */
    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event){
        log.info("WebSocket Subscribe:{},user:{}",event.getMessage(),event.getUser());
    }

    /***
     * 取消订阅消息
     * @param event
     */
    @EventListener
    public void handleWebSocketUnSubscribeListener(SessionUnsubscribeEvent event){
        log.info("WebSocket UnSubscribe:{},user:{}",event.getMessage(),event.getUser());
    }


    /***
     * 监听关闭连接消息
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        log.info("User Disconnected :{},user:{} " ,event.getMessage(),event.getUser());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if(username != null) {
//            log.info("User Disconnected : " + username);
//            ChatMessage chatMessage = new ChatMessage();
//            chatMessage.setType(ChatMessage.MessageType.LEAVE);
//            chatMessage.setSender(username);
//            messagingTemplate.convertAndSend("/topic/public", chatMessage);
//        }
    }
}