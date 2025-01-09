package com.gallery.websoket.web.websoket;


import com.gallery.websoket.exception.BizException;
import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.result.R;
import com.gallery.websoket.service.MyWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageWebSoketController {


    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageWebSoketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 发送消息给特定用户
     */
    @MessageMapping("/sendToUser")
    public void sendToUser(String message, StompHeaderAccessor accessor) {
        // 获取 userId
        String userId = (String) accessor.getSessionAttributes().get("userId");
        log.info("发送给特定用户 {} 的消息: {}", userId, message);
        // 向特定用户发送消息
        messagingTemplate.convertAndSendToUser(userId, "/queue/reply", "Direct message: " + message);
    }

    /**
     * 广播消息给所有用户
     */
    @MessageMapping("/sendMessage")
    public void sendMessage(String message) {
        log.info("收到客户端消息: {}", message);
        // 广播消息给所有用户
        messagingTemplate.convertAndSend("/topic/greetings", "Hello, " + message);
    }
}
