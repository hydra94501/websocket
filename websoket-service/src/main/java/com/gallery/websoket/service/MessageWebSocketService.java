package com.gallery.websoket.service;

import com.gallery.websoket.enums.MessageTargetType;
import com.gallery.websoket.factory.MessagePushStrategyFactory;
import com.gallery.websoket.model.MyWebSocketMessage;
import org.springframework.stereotype.Service;

@Service
public class MessageWebSocketService {


    private final MessagePushStrategyFactory strategyFactory;


    public MessageWebSocketService(MessagePushStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public void sendMessage(MessageTargetType targetType, MyWebSocketMessage message) {
        // 根据目标类型选择推送策略并发送消息
        MessagePushStrategy strategy = strategyFactory.getStrategy(targetType);
        if (strategy != null) {
            strategy.sendMessage(message);
        } else {
            throw new IllegalArgumentException("Unsupported target type: " + targetType);
        }
    }


}
