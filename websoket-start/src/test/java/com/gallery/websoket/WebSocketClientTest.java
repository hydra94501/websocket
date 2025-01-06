//package com.gallery.websoket;
//
//import com.gallery.websoket.handler.MyWebSocketHandler;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.client.WebSocketClient;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//public class WebSocketClientTest {
//
//    @Autowired
//    private MyWebSocketHandler myWebSocketHandler;
//
//    @Test
//    public void testWebSocketConnection() throws Exception {
//        WebSocketClient webSocketClient = new StandardWebSocketClient();
//        // 客户端通过 URL 查询参数传递 userId
//        String userId = "111";
//        String url = "ws://localhost:8080/websocket";
//
//        // 手动传递 userId 到 WebSocketSession
//
//        WebSocketSession session =  webSocketClient.doHandshake(myWebSocketHandler, url).get();
//
//        // 通过WebSocketSession的Attributes设置userId
//        session.getAttributes().put("userId", userId);
//        if (session.isOpen()) {
//            session.sendMessage(new TextMessage("Hello WebSocket Server!"));
//        } else {
//            System.out.println("WebSocket session is already closed.");
//        }
//        // Wait for some time to ensure message processing (you can adjust the sleep time as needed)
//        Thread.sleep(1000);
//
//        // Here, you could assert or validate the behavior, for example, checking server logs or messages.
//    }
//}