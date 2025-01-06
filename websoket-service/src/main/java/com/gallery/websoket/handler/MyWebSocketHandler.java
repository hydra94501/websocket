package com.gallery.websoket.handler;

import com.alibaba.fastjson.JSONObject;
import com.gallery.websoket.model.MyWebSocketMessage;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

public class MyWebSocketHandler extends TextWebSocketHandler {

    // 存储已连接的客户端
    private final Map<Long, WebSocketSession> userSessions = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 客户端连接建立后触发
        String userId = (String) session.getAttributes().get("userId");
//        String userId = (String) session.getUri().getQuery();
        if (userId != null) {
            userSessions.put(Long.parseLong(userId), session);
            System.out.println("客户端 " + userId + " 已连接，session ID: " + session.getId());
        } else {
            System.out.println("客户端未提供用户ID，无法建立连接");
            session.close();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的消息
        String payload = message.getPayload();
        //消息转换为json
        System.out.println("收到客户端消息: " + payload);
        if (!StringUtils.isEmpty(payload)) {
            try {
                JSONObject messageJson = JSONObject.parseObject(payload);
                // 你可以在此处将消息解析为 WebSocketMessage 或进行其他业务逻辑处理
                MyWebSocketMessage webSocketMessage = new MyWebSocketMessage();
                webSocketMessage.setContent(messageJson.get("content").toString());
                // 例如，发送给发送者自己
                sendMessageToUser(Long.parseLong(session.getAttributes().get("userId").toString()), webSocketMessage);
            } catch (Exception e) {
                throw new RuntimeException("消息接收失败，消息格式错误！");
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 客户端断开连接时触发
        String userId = (String) session.getAttributes().get("userId");
        if (userId != null) {
            userSessions.remove(Long.parseLong(userId));
            System.out.println("客户端 " + userId + " 已断开连接");
        }
    }

    // 发送消息给指定的用户
    public void sendMessageToUser(Long userId, MyWebSocketMessage message) throws Exception {
        WebSocketSession session = userSessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
            System.out.println("消息发送给用户 " + userId + ": " + message.getContent());
        } else {
            throw new RuntimeException("无法发送消息给用户 " + userId + "，用户连接已关闭");
        }
    }

    // 发送消息给所有连接的客户端
    public void sendMessageToAll(MyWebSocketMessage message) throws Exception {
        for (WebSocketSession session : userSessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
                System.out.println("消息广播给用户: " + session.getId());
            }
        }
    }
}