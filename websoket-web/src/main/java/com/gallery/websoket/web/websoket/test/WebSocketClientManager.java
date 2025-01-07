package com.gallery.websoket.web.websoket.test;

import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WebSocketClientManager {

    private static final String SERVER_URI = "ws://192.168.0.109:8081/websocket";  // 替换为实际WebSocket服务器URI

    public static void main(String[] args) {
        List<WebSocketSession> sessions = new ArrayList<>();

        // 创建 WebSocket 客户端
        WebSocketClient client = new StandardWebSocketClient();

        // 创建 WebSocketHandler，处理连接的消息
        WebSocketHandler handler = new TextWebSocketHandler() {
            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                System.out.println("收到消息: " + message.getPayload());
            }

            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                System.out.println("连接成功!");
                // 连接建立后可以发送消息
                session.sendMessage(new TextMessage("{\"type\":\"message\",\"content\":\"大哥你好\"}"));
            }

            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                System.err.println("连接错误: " + exception.getMessage());
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                System.out.println("连接已关闭: " + status.getReason());
            }
        };

        // 创建并连接1000个客户端
        for (int i = 0; i <= 10000; i++) {
            try {
                String userId = UUID.randomUUID().toString();
                WebSocketSession session = client.doHandshake(handler, SERVER_URI+"?userId="+userId).get();
                sessions.add(session);
                System.out.println("客户端 " + (i + 1) + " 已连接。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 等待客户端连接并处理消息
        // 在此处可以根据需要添加等待机制，确保客户端能够接收到消息


        // 在此处可以加入一个机制等待所有客户端完成消息收发等
        // 例如：等待一段时间，确保所有客户端能够正常交互
        try {
            Thread.sleep(50000000);  // 等待5秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 在这里可以处理每个WebSocketSession对象的后续逻辑
        // 例如：处理客户端的响应、关闭连接等
//        for (WebSocketSession session : sessions) {
//            try {
//                // 确保连接正常关闭
//                session.close();
//                System.out.println("客户端连接已关闭.");
//            } catch (Exception e) {
//                System.err.println("关闭连接失败: " + e.getMessage());
//            }
//        }
    }
}

