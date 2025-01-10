package com.gallery.websoket.service.impl.message;

import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.service.MessagePushStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GroupPushStrategy implements MessagePushStrategy {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GroupPushStrategy(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendMessage(MyWebSocketMessage message) {
        // 向指定群组发送消息
        messagingTemplate.convertAndSend("/topic/group/" + message.getExtensionField(), message);
    }
}
