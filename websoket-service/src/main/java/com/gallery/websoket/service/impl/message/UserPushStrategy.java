package com.gallery.websoket.service.impl.message;

import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.service.MessagePushStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserPushStrategy implements MessagePushStrategy {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UserPushStrategy(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendMessage(MyWebSocketMessage message) {
        messagingTemplate.convertAndSendToUser(message.getReceiveUserId(), "/queue/notifications", message);
    }
}