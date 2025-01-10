package com.gallery.websoket.web.message;

import com.gallery.websoket.service.MessageWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageWebSoketController {

    private final MessageWebSocketService messageWebSocketService;

    @Autowired
    public MessageWebSoketController(MessageWebSocketService messageWebSocketService) {
        this.messageWebSocketService = messageWebSocketService;
    }

    /**
     * 发送消息给特定用户
     */
    @MessageMapping("/sendToUser")
    public void sendToUser(String message, StompHeaderAccessor accessor) {
        log.info("收到发送给特定用户的消息: {}", message);
        messageWebSocketService.sendToUser(message, accessor);
    }

    /**
     * 广播消息给所有用户
     */
    @MessageMapping("/sendMessage")
    public void sendMessage(String message) {
        log.info("收到广播消息: {}", message);
        messageWebSocketService.sendMessage(message);
    }
}

