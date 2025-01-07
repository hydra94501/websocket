package com.gallery.websoket.handler;

import com.alibaba.fastjson.JSONObject;
import com.gallery.websoket.model.MyWebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;


@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

    // 存储已连接的客户端
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    @Autowired
    private ExecutorService executorService; // 注入自定义线程池

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 客户端连接建立后触发
        String userId = (String) session.getAttributes().get("userId");
//        String userId = (String) session.getUri().getQuery();
        if (userId != null) {
            userSessions.put(userId, session);
            System.out.println("客户端 " + userId + " 已连接，session ID: " + session.getId());
        } else {
            log.info("客户端未提供用户ID，无法建立连接");
            session.close();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的消息
        String payload = message.getPayload();
        //消息转换为json
        log.info("收到客户端消息: " + payload);
        if (!StringUtils.isEmpty(payload)) {
            try {
                JSONObject messageJson = JSONObject.parseObject(payload);
                // 你可以在此处将消息解析为 WebSocketMessage 或进行其他业务逻辑处理
                MyWebSocketMessage webSocketMessage = new MyWebSocketMessage();
                webSocketMessage.setContent(messageJson.get("content").toString());
                // 例如，发送给发送者自己
                sendMessageToUser(session.getAttributes().get("userId").toString(), webSocketMessage);
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
            userSessions.remove(userId);
            log.info("客户端 " + userId + " 已断开连接");
        }
    }

    // 发送消息给指定的用户
    public void sendMessageToUser(String userId, MyWebSocketMessage message) throws Exception {
        WebSocketSession session = userSessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
            log.info("消息发送给用户 " + userId + ": " + message.getContent());
        } else {
            throw new RuntimeException("无法发送消息给用户 " + userId + "，用户连接已关闭");
        }
    }

    // 发送消息给所有连接的客户端
    public void sendMessageToAll(MyWebSocketMessage message) throws Exception {
        Integer count = 0;
        for (WebSocketSession session : userSessions.values()) {
            if (session.isOpen()) {
                // 使用线程池异步处理每个 WebSocket 会话的消息发送
                executorService.submit(new BroadcastTask(session, message, count++));
//                session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
                log.info("消息广播给用户: " + session.getId());
            }
        }
    }

    // 定义一个任务来异步发送消息
    private static class BroadcastTask implements Callable<Void> {
        private WebSocketSession session;
        private MyWebSocketMessage message;
        private Integer count;

        public BroadcastTask(WebSocketSession session, MyWebSocketMessage message, Integer count) {
            this.session = session;
            this.message = message;
            this.count = count;
        }
        @Override
        public Void call() {
            try {
                // 发送消息给客户端
                session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
                log.info("消息广播count " + count);
                log.info("消息广播给用户: " + session.getId());
            } catch (Exception e) {
                log.info("发送消息给用户失败: " + session.getId());
                e.printStackTrace();
            }
            return null;
        }
    }
}