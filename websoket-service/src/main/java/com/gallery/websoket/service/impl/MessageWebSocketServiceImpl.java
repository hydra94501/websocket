package com.gallery.websoket.service.impl;

import com.gallery.websoket.handler.MyWebSocketHandler;
import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.service.MessageWebSocketService;
import com.gallery.websoket.service.MyWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageWebSocketServiceImpl implements MessageWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageWebSocketServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 向特定用户发送消息
     */
    public void sendToUser(String message, StompHeaderAccessor accessor) {
        // 获取 userId
        String userId = accessor.getNativeHeader("receiver").get(0);
        if (userId == null) {
            log.error("用户ID为空，发送失败！");
            return;
        }
        log.info("发送给特定用户 {} 的消息: {}", userId, message);
        // 向特定用户发送消息
        messagingTemplate.convertAndSendToUser(userId, "/queue/reply", "Direct message: " + message);
    }

    /**
     * 广播消息给所有用户
     */
    public void sendMessage(String message) {
        log.info("收到广播消息: {}", message);
        messagingTemplate.convertAndSend("/topic/*", "Hello, " + message);
    }
}
