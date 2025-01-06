package com.gallery.websoket.demos.web.websoket;


import com.gallery.websoket.model.MyWebSocketMessage;
import com.gallery.websoket.service.MyWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {

    private final MyWebSocketService myWebSocketService;

    @Autowired
    public WebSocketController(MyWebSocketService myWebSocketService) {
        this.myWebSocketService = myWebSocketService;
    }

    /**
     * 发送消息给指定用户
     *
     * @param message WebSocket 消息
     * @return 响应结果1
     */
    @PostMapping("/send")
    public String sendMessage(@RequestBody MyWebSocketMessage message) {
        try {
            myWebSocketService.sendMessageToUser(message);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Failed to send message: " + e.getMessage();
        }
    }

    /**
     * 广播消息给所有已连接的用户
     *
     * @param message WebSocket 消息
     * @return 响应结果
     */
    @PostMapping("/broadcast")
    public String broadcastMessage(@RequestBody MyWebSocketMessage message) {
        try {
            myWebSocketService.broadcastMessage(message);
            return "Message broadcasted successfully!";
        } catch (Exception e) {
            return "Failed to broadcast message: " + e.getMessage();
        }
    }
}
