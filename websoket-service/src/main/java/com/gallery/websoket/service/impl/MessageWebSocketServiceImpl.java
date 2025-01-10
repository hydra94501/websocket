//package com.gallery.websoket.service.impl;
//
//import com.gallery.websoket.service.MessageWebSocketService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class MessageWebSocketServiceImpl implements MessageWebSocketService {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    public MessageWebSocketServiceImpl(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    /**
//     * 向特定用户发送消息
//     */
//    public void sendToUser(String userId, String message) {
//        log.info("发送给特定用户 {} 的消息: {}", userId, message);
//        // 向特定用户发送消息
//        messagingTemplate.convertAndSendToUser(userId, "/queue/reply", "Direct message: " + message);
//    }
//
//    /**
//     * topic发送用户消息
//     *
//     * @param topicName topic表里唯一标识
//     * @param message
//     */
//    public void sendToTopic(String topicName, String message) {
//        messagingTemplate.convertAndSend("/topic/" + topicName, message);
//    }
//
//    /**
//     * topic发送群组消息
//     *
//     * @param topicName topic表里唯一标识
//     * @param message
//     */
//    public void sendToGroup(String topicName, String groupId, String message) {
//        messagingTemplate.convertAndSend("/topic/group/" + groupId, message);
//    }
//
//    /**
//     * 广播消息给所有用户
//     */
//    public void sendToAll(String message) {
//        log.info("收到广播消息: {}", message);
//        messagingTemplate.convertAndSend("/topic/all", "Hello, " + message);
//    }
//}
