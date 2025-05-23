//package com.gallery.websoket.web.websoket;
//
//
//import com.gallery.websoket.exception.BizException;
//import com.gallery.websoket.dto.parm.MyWebSocketMessage;
//import com.gallery.websoket.result.R;
//import com.gallery.websoket.service.MyWebSocketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/websocket")
//public class WebSocketController {
//
//    private final MyWebSocketService myWebSocketService;
//
//    @Autowired
//    public WebSocketController(MyWebSocketService myWebSocketService) {
//        this.myWebSocketService = myWebSocketService;
//    }
//
//    /**
//     * 发送消息给指定用户
//     *
//     * @param message WebSocket 消息
//     * @return 响应结果1
//     */
//    @MessageMapping("/send2user")
//    public R sendMessage(@RequestBody MyWebSocketMessage message) {
//        try {
//            myWebSocketService.sendMessageToUser(message);
//            return R.ok();
//        } catch (Exception e) {
//            throw new BizException("发送失败");
//        }
//    }
//
//    /**
//     * 广播消息给所有已连接的用户
//     *
//     * @param message WebSocket 消息
//     * @return 响应结果
//     */
//    @PostMapping("/broadcast")
//    public R broadcastMessage(@RequestBody MyWebSocketMessage message) {
//        try {
//            myWebSocketService.broadcastMessage(message);
//            return R.ok();
//        } catch (Exception e) {
//            throw new BizException("发送失败");
//        }
//    }
//}
