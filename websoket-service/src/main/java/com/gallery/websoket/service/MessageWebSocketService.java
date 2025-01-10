package com.gallery.websoket.service;

import com.gallery.websoket.model.MyWebSocketMessage;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public interface MessageWebSocketService {

    /**
     * 发送消息给指定用户
     *
     * @param message WebSocket 消息
     */
    void sendToUser(String message, StompHeaderAccessor accessor);

    /**
     * 广播消息给所有已连接的用户
     *
     * @param message WebSocket 消息
     */
    void sendMessage(String message);

}
