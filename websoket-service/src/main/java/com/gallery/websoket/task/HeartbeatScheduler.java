package com.gallery.websoket.task;

import com.gallery.websoket.manager.WebSocketSessionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HeartbeatScheduler {

    private final WebSocketSessionManager sessionManager;

    public HeartbeatScheduler(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    // 每隔30秒执行一次检查，检查连接是否超时
    @Scheduled(fixedRate = 30000) // 每30秒执行一次
    public void checkHeartbeats() {
        sessionManager.checkHeartbeatTimeout(60000);  // 超时设置为60秒
    }
}