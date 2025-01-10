package com.gallery.websoket.web.message;

import com.gallery.websoket.enums.MessageTargetType;
import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.service.MessageWebSocketService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageWebSoketController {

    private final MessageWebSocketService messageWebSocketService;

    @Autowired
    public MessageWebSoketController(MessageWebSocketService messageWebSocketService) {
        this.messageWebSocketService = messageWebSocketService;
    }

    @ApiOperation(value = "向指定用户发送消息")
    @PostMapping("/sendToUser")
    public void sendToUser(@RequestBody MyWebSocketMessage message) {
        messageWebSocketService.sendMessage(MessageTargetType.USER, message);
    }

    @ApiOperation(value = "向指定群组发送消息")
    @PostMapping("/sendToGroup")
    public void sendToGroup(@RequestBody MyWebSocketMessage message) {
        messageWebSocketService.sendMessage(MessageTargetType.GROUP, message);
    }

    @ApiOperation(value = "向所有用户发送消息")
    @PostMapping("/sendToAll")
    public void sendToAll(@RequestBody MyWebSocketMessage message) {
        messageWebSocketService.sendMessage(MessageTargetType.ALL, message);
    }

    @ApiOperation(value = "向指定Topic发送消息")
    @PostMapping("/sendToTopic")
    public void sendToTopic(@RequestBody MyWebSocketMessage message) {
        messageWebSocketService.sendMessage(MessageTargetType.TOPIC, message);
    }
}

