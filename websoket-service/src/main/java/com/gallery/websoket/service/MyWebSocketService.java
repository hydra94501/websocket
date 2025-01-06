package com.gallery.websoket.service;

import com.gallery.websoket.model.MyWebSocketMessage;

public interface MyWebSocketService {

    /**
     * 发送消息给指定用户
     *
     * @param message WebSocket 消息
     */
    void sendMessageToUser(MyWebSocketMessage message) throws Exception;

    /**
     * 广播消息给所有已连接的用户
     *
     * @param message WebSocket 消息
     */
    void broadcastMessage(MyWebSocketMessage message) throws Exception;

    /**
     * 获取指定用户的消息
     *
     * @param userId 用户ID
     * @return WebSocketMessage
     */
    MyWebSocketMessage getMessageByUserId(Long userId);
}
