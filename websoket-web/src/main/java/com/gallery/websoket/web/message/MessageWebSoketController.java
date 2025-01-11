package com.gallery.websoket.web.message;

import com.gallery.websoket.dto.res.MessagePushRes;
import com.gallery.websoket.enums.MessageTargetType;
import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import com.gallery.websoket.result.R;
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
    public R<MessagePushRes> sendToUser(@RequestBody MyWebSocketMessage message) {
        return R.ok(messageWebSocketService.sendMessage(MessageTargetType.USER, message));
    }

    @ApiOperation(value = "向指定群组发送消息")
    @PostMapping("/sendToGroup")
    public R<MessagePushRes> sendToGroup(@RequestBody MyWebSocketMessage message) {
        return R.ok(messageWebSocketService.sendMessage(MessageTargetType.GROUP, message));
    }

    @ApiOperation(value = "向所有用户发送消息")
    @PostMapping("/sendToAll")
    public R<MessagePushRes> sendToAll(@RequestBody MyWebSocketMessage message) {
        return R.ok(messageWebSocketService.sendMessage(MessageTargetType.ALL, message));
    }

    @ApiOperation(value = "向指定Topic发送消息")
    @PostMapping("/sendToTopic")
    public R<MessagePushRes> sendToTopic(@RequestBody MyWebSocketMessage message) {
        return R.ok(messageWebSocketService.sendMessage(MessageTargetType.TOPIC, message));
    }
}

