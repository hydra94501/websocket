package com.gallery.websoket.service.impl;

import com.gallery.websoket.handler.MyWebSocketHandler;
import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.service.MyWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyWebSocketServiceImpl implements MyWebSocketService {

    private final MyWebSocketHandler myWebSocketHandler;

    @Autowired
    public MyWebSocketServiceImpl(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @Override
    public void sendMessageToUser(MyWebSocketMessage message) throws Exception {
        Long receiveUserId = message.getReceiveUserId();
        myWebSocketHandler.sendMessageToUser(receiveUserId, message);
    }

    @Override
    public void broadcastMessage(MyWebSocketMessage message) throws Exception {
        myWebSocketHandler.sendMessageToAll(message);
    }

    @Override
    public MyWebSocketMessage getMessageByUserId(Long userId) {
        // 可以通过 Redis 或数据库获取某个用户的消息
        return new MyWebSocketMessage(); // 示例返回
    }
}
