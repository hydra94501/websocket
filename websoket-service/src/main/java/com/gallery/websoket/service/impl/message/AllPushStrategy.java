package com.gallery.websoket.service.impl.message;

import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import com.gallery.websoket.dto.res.MessagePushRes;
import com.gallery.websoket.service.MessagePushStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AllPushStrategy implements MessagePushStrategy {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public AllPushStrategy(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public MessagePushRes sendMessage(MyWebSocketMessage message) {
        // 向所有用户发送消息
        messagingTemplate.convertAndSend("/topic/all", message);
        MessagePushRes messagePushRes = new MessagePushRes();
        messagePushRes.setStatus("SUCCESS");
        return messagePushRes;
    }
}